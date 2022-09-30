package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.sdk.util.Constants;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.handler.NoteBookOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class NoteBookOperateHandlerImpl implements NoteBookOperateHandler {

    private final NoteBookMaintainService noteBookMaintainService;
    private final PonbMaintainService ponbMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public NoteBookOperateHandlerImpl(
            NoteBookMaintainService noteBookMaintainService,
            PonbMaintainService ponbMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.noteBookMaintainService = noteBookMaintainService;
        this.ponbMaintainService = ponbMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createNoteBook(StringIdKey userKey, NoteBookCreateInfo noteBookCreateInfo)
            throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 根据 noteBookCreateInfo 以及创建的规则组合 笔记本 实体。
            Date currentDate = new Date();
            NoteBook noteBook = new NoteBook(
                    null, noteBookCreateInfo.getName(), noteBookCreateInfo.getRemark(), currentDate,
                    0, currentDate, currentDate
            );

            // 3. 插入笔记本实体，并获取生成的主键。
            LongIdKey noteBookKey = noteBookMaintainService.insert(noteBook);

            // 4. 由笔记本实体生成的主键和用户主键组合权限信息，并插入。
            Ponb ponb = new Ponb(
                    new PonbKey(noteBookKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建笔记本时自动插入，赋予创建人所有者权限"
            );
            ponbMaintainService.insert(ponb);

            // 5. 返回生成的主键。
            return noteBookKey;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey noteBookKey = noteBookUpdateInfo.getNoteBookKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 3. 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(userKey, noteBookKey);

            // 4. 根据 noteBookUpdateInfo 以及更新的规则设置 笔记本 实体。
            NoteBook noteBook = noteBookMaintainService.get(noteBookKey);
            noteBook.setName(noteBookUpdateInfo.getName());
            noteBook.setRemark(noteBookUpdateInfo.getRemark());
            Date currentDate = new Date();
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);

            // 5. 更新笔记本实体。
            noteBookMaintainService.update(noteBook);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeNoteBook(StringIdKey userKey, LongIdKey noteBookKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 3. 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(userKey, noteBookKey);

            // 4. 删除指定主键的笔记本。
            noteBookMaintainService.delete(noteBookKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, NoteBookPermissionUpsertInfo noteBookPermissionUpsertInfo
    ) throws HandlerException {
        try {
            LongIdKey noteBookKey = noteBookPermissionUpsertInfo.getNoteBookKey();
            StringIdKey targetUserKey = noteBookPermissionUpsertInfo.getUserKey();
            int permissionLevel = noteBookPermissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            operateHandlerValidator.makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(ownerUserKey);
            operateHandlerValidator.makeSureUserExists(targetUserKey);

            // 4. 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 5. 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(ownerUserKey, noteBookKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Constants.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Constants.PERMISSION_LEVEL_OWNER:
                    permissionLabel = "所有者";
                    break;
                default:
                    permissionLabel = "（未知）";
            }
            Ponb ponb = new Ponb(
                    new PonbKey(noteBookKey.getLongId(), targetUserKey.getStringId()),
                    permissionLevel,
                    "赋予用户 " + targetUserKey.getStringId() + " " + permissionLabel + "权限"
            );
            ponbMaintainService.insertOrUpdate(ponb);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, NoteBookPermissionRemoveInfo noteBookPermissionRemoveInfo
    ) throws HandlerException {
        try {
            LongIdKey noteBookKey = noteBookPermissionRemoveInfo.getNoteBookKey();
            StringIdKey targetUserKey = noteBookPermissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(ownerUserKey);
            operateHandlerValidator.makeSureUserExists(targetUserKey);

            // 3. 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 4. 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(ownerUserKey, noteBookKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PonbKey ponbKey = new PonbKey(noteBookKey.getLongId(), targetUserKey.getStringId());
            ponbMaintainService.deleteIfExists(ponbKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
