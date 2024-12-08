package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.sdk.util.Constants;
import com.dwarfeng.familyhelper.note.stack.bean.dto.*;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.handler.NoteBookOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.FavoriteMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
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
    private final FavoriteMaintainService favoriteMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public NoteBookOperateHandlerImpl(
            NoteBookMaintainService noteBookMaintainService,
            PonbMaintainService ponbMaintainService,
            FavoriteMaintainService favoriteMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.noteBookMaintainService = noteBookMaintainService;
        this.ponbMaintainService = ponbMaintainService;
        this.favoriteMaintainService = favoriteMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createNoteBook(StringIdKey userKey, NoteBookCreateInfo noteBookCreateInfo)
            throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 根据 noteBookCreateInfo 以及创建的规则组合 笔记本 实体。
            Date currentDate = new Date();
            NoteBook noteBook = new NoteBook(
                    null, noteBookCreateInfo.getName(), noteBookCreateInfo.getRemark(), currentDate,
                    0, currentDate, currentDate
            );

            // 插入笔记本实体，并获取生成的主键。
            LongIdKey noteBookKey = noteBookMaintainService.insert(noteBook);

            // 由笔记本实体生成的主键和用户主键组合权限信息，并插入。
            Ponb ponb = new Ponb(
                    new PonbKey(noteBookKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建笔记本时自动插入，赋予创建人所有者权限"
            );
            ponbMaintainService.insert(ponb);

            // 如果是收藏，则插入收藏实体。
            if (noteBookCreateInfo.isFavorite()) {
                Favorite favorite = new Favorite(
                        new FavoriteKey(noteBookKey.getLongId(), userKey.getStringId()),
                        "通过 familyhelper-note 服务上传/更新留言附件"
                );
                favoriteMaintainService.insertOrUpdate(favorite);
            }

            // 返回生成的主键。
            return noteBookKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey noteBookKey = noteBookUpdateInfo.getNoteBookKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(userKey, noteBookKey);

            // 根据 noteBookUpdateInfo 以及更新的规则设置 笔记本 实体。
            NoteBook noteBook = noteBookMaintainService.get(noteBookKey);
            noteBook.setName(noteBookUpdateInfo.getName());
            noteBook.setRemark(noteBookUpdateInfo.getRemark());
            Date currentDate = new Date();
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);

            // 更新笔记本实体。
            noteBookMaintainService.update(noteBook);

            // 如果是收藏，则插入收藏实体。
            FavoriteKey favoriteKey = new FavoriteKey(noteBookKey.getLongId(), userKey.getStringId());
            if (noteBookUpdateInfo.isFavorite()) {
                Favorite favorite = new Favorite(favoriteKey, "通过 familyhelper-note 服务创建/更新");
                favoriteMaintainService.insertOrUpdate(favorite);
            }
            // 否则删除收藏实体。
            else {
                favoriteMaintainService.deleteIfExists(favoriteKey);
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
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
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了程序的可读性，此处不做代码提取。
    @SuppressWarnings("ExtractMethodRecommender")
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
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
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
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void changeFavored(StringIdKey operateUserKey, NoteBookFavoredChangeInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey noteBookKey = info.getNoteBookKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(operateUserKey);
            // 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);
            // 确认用户有权限查看指定的笔记本。
            operateHandlerValidator.makeSureUserInspectPermittedForNoteBook(operateUserKey, noteBookKey);

            // 构造收藏实体主键。
            FavoriteKey favoriteKey = new FavoriteKey(noteBookKey.getLongId(), operateUserKey.getStringId());

            // 如果主键对应的实体存在，则删除实体。
            if (favoriteMaintainService.exists(favoriteKey)) {
                favoriteMaintainService.delete(favoriteKey);
            }
            // 否则，插入实体。
            else {
                Favorite favorite = new Favorite(favoriteKey, "通过 familyhelper-note 服务创建/更新");
                favoriteMaintainService.insertOrUpdate(favorite);
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
