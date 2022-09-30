package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.handler.NoteNodeOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteNodeMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class NoteNodeOperateHandlerImpl implements NoteNodeOperateHandler {

    private final NoteNodeMaintainService noteNodeMaintainService;
    private final NoteBookMaintainService noteBookMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public NoteNodeOperateHandlerImpl(
            NoteNodeMaintainService noteNodeMaintainService,
            NoteBookMaintainService noteBookMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.noteNodeMaintainService = noteNodeMaintainService;
        this.noteBookMaintainService = noteBookMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createNoteNode(StringIdKey userKey, NoteNodeCreateInfo noteNodeCreateInfo)
            throws HandlerException {
        try {
            LongIdKey bookKey = noteNodeCreateInfo.getBookKey();
            LongIdKey parentKey = noteNodeCreateInfo.getParentKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认父笔记节点存在。
            if (Objects.nonNull(parentKey)) {
                operateHandlerValidator.makeSureNoteNodeExists(parentKey);
            }

            // 确认用户有权限操作指定的笔记本。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(userKey, bookKey);

            // 确认笔记节点与父笔记节点的笔记本存在。
            operateHandlerValidator.makeSureNoteBookIdenticalForNoteBook(parentKey, bookKey);

            // 更新笔记本实体的字段。
            Date currentDate = new Date();
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 根据 noteNodeCreateInfo 以及创建的规则组合 笔记节点 实体。
            NoteNode noteNode = new NoteNode(
                    null, parentKey, bookKey, noteNodeCreateInfo.getName(), noteNodeCreateInfo.getRemark()
            );

            // 插入笔记节点，并返回笔记节点实体的主键。
            return noteNodeMaintainService.insert(noteNode);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateNoteNode(StringIdKey userKey, NoteNodeUpdateInfo noteNodeUpdateInfo) throws HandlerException {
        try {
            LongIdKey noteNodeKey = noteNodeUpdateInfo.getKey();
            LongIdKey parentKey = noteNodeUpdateInfo.getParentKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记节点存在。
            operateHandlerValidator.makeSureNoteNodeExists(noteNodeKey);

            // 确认父笔记节点存在。
            if (Objects.nonNull(parentKey)) {
                operateHandlerValidator.makeSureNoteNodeExists(parentKey);
            }

            // 确认笔记本存在。
            NoteNode noteNode = noteNodeMaintainService.get(noteNodeKey);
            LongIdKey bookKey = noteNode.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的笔记节点。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteNode(userKey, noteNodeKey);

            // 确认笔记节点与父笔记节点的笔记本存在。
            operateHandlerValidator.makeSureNoteBookIdenticalForNoteNode(parentKey, noteNodeKey);

            // 更新笔记本实体的字段。
            Date currentDate = new Date();
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 根据 noteNodeUpdateInfo 以及更新的规则设置 笔记节点 实体。
            noteNode.setParentKey(parentKey);
            noteNode.setName(noteNodeUpdateInfo.getName());
            noteNode.setRemark(noteNodeUpdateInfo.getRemark());

            // 更新 笔记节点 实体。
            noteNodeMaintainService.update(noteNode);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记节点存在。
            operateHandlerValidator.makeSureNoteNodeExists(noteNodeKey);

            // 确认笔记本存在。
            NoteNode noteNode = noteNodeMaintainService.get(noteNodeKey);
            LongIdKey bookKey = noteNode.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的笔记本节点。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteNode(userKey, noteNodeKey);

            // 更新笔记本实体的字段。
            Date currentDate = new Date();
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 存在删除指定的笔记节点。
            noteNodeMaintainService.deleteIfExists(noteNodeKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
