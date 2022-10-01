package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.handler.NoteItemOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
public class NoteItemOperateHandlerImpl implements NoteItemOperateHandler {

    private final NoteItemMaintainService noteItemMaintainService;
    private final NoteBookMaintainService noteBookMaintainService;

    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    private final OperateHandlerValidator operateHandlerValidator;

    public NoteItemOperateHandlerImpl(
            NoteItemMaintainService noteItemMaintainService,
            NoteBookMaintainService noteBookMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.noteItemMaintainService = noteItemMaintainService;
        this.noteBookMaintainService = noteBookMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createNoteItem(StringIdKey userKey, NoteItemCreateInfo noteItemCreateInfo) throws HandlerException {
        try {
            LongIdKey bookKey = noteItemCreateInfo.getBookKey();
            LongIdKey nodeKey = noteItemCreateInfo.getNodeKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认笔记节点存在。
            if (Objects.nonNull(nodeKey)) {
                operateHandlerValidator.makeSureNoteNodeExists(nodeKey);
            }

            // 确认用户有权限操作指定的笔记节点。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteBook(userKey, bookKey);

            // 确认笔记节点与父笔记节点的笔记本存在。
            operateHandlerValidator.makeSureNoteBookIdenticalForNoteBook(nodeKey, bookKey);

            // 分配主键。
            LongIdKey noteItemKey = keyFetcher.fetchKey();

            // 上传空文件。
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_NOTE_FILE}, getFileName(noteItemKey), new byte[0]);

            // 根据 noteItemCreateInfo 以及创建的规则组合 笔记项目 实体，并调用维护服务插入。
            Date currentDate = new Date();
            int index = Optional.ofNullable(noteItemMaintainService.lookupFirst(
                    NoteItemMaintainService.CHILD_FOR_NODE_INDEX_DESC, new Object[]{nodeKey}
            )).map(item -> item.getIndex() + 1).orElse(0);
            NoteItem noteItem = new NoteItem(
                    noteItemKey, nodeKey, bookKey, noteItemCreateInfo.getName(), noteItemCreateInfo.getRemark(),
                    0, currentDate, currentDate, currentDate, index
            );
            noteItemMaintainService.insert(noteItem);

            // 更新笔记本字段值，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setItemCount(noteBook.getItemCount() + 1);

            noteBook.setLastInspectedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 返回主键。
            return noteItemKey;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateNoteItem(StringIdKey userKey, NoteItemUpdateInfo noteItemUpdateInfo) throws HandlerException {
        try {
            LongIdKey noteItemKey = noteItemUpdateInfo.getKey();
            LongIdKey noteNodeKey = noteItemUpdateInfo.getNodeKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记项目存在。
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            LongIdKey oldNoteNodeKey = noteItem.getNodeKey();

            // 确认笔记本存在。
            LongIdKey bookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的笔记项目。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteItem(userKey, noteItemKey);

            // 确认笔记节点与父笔记节点的笔记本存在。
            if (!Objects.equals(noteNodeKey, oldNoteNodeKey)) {
                operateHandlerValidator.makeSureNoteBookIdenticalForNoteNode(oldNoteNodeKey, noteNodeKey);
            }

            // 更新笔记本字段值，并调用维护服务进行更新。
            Date currentDate = new Date();
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setItemCount(noteBook.getItemCount() + 1);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 根据 noteItemUpdateInfo 以及更新的规则设置 笔记项目 实体，并调用维护服务进行更新。
            noteItem.setName(noteItemUpdateInfo.getName());
            noteItem.setRemark(noteItemUpdateInfo.getRemark());
            if (!Objects.equals(noteNodeKey, oldNoteNodeKey)) {
                noteItem.setNodeKey(noteNodeKey);
            }
            noteItemMaintainService.update(noteItem);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeNoteItem(StringIdKey userKey, LongIdKey noteItemKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记项目存在。
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            LongIdKey bookKey = noteItem.getBookKey();

            // 确认笔记本存在。
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteItem(userKey, noteItemKey);

            // 存在删除指定的笔记项目。
            noteItemMaintainService.delete(noteItemKey);

            // 更新笔记本字段值，并调用维护服务进行更新。
            Date currentDate = new Date();
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setItemCount(Math.max(noteBook.getItemCount() - 1, 0));
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public NoteFile downloadNoteFile(StringIdKey userKey, LongIdKey noteItemKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记项目存在。
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);

            // 确认笔记本存在。
            LongIdKey bookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的笔记项目。
            operateHandlerValidator.makeSureUserInspectPermittedForNoteItem(userKey, noteItemKey);

            // 下载个人最佳文件。
            byte[] content = ftpHandler.getFileContent(
                    new String[]{FtpConstants.PATH_NOTE_FILE}, getFileName(noteItemKey)
            );

            // 更新笔记项目的查看时间，并调用维护服务进行更新。
            Date currentDate = new Date();
            noteItem.setInspectedDate(new Date());
            noteItemMaintainService.update(noteItem);

            // 更新笔记本字段值，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 返回笔记文件。
            return new NoteFile(noteItemKey, content);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadNoteFile(StringIdKey userKey, NoteFileUploadInfo noteFileUploadInfo) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记项目存在。
            LongIdKey noteItemKey = noteFileUploadInfo.getNoteItemKey();
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);

            // 确认笔记本存在。
            LongIdKey bookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(bookKey);

            // 确认用户有权限操作指定的笔记项目。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteItem(userKey, noteItemKey);

            // 笔记文件内容并存储（覆盖）。
            byte[] content = noteFileUploadInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_NOTE_FILE}, getFileName(noteItemKey), content);

            // 更新笔记项目的查看时间，并调用维护服务进行更新。
            Date currentDate = new Date();
            noteItem.setInspectedDate(new Date());
            noteItem.setModifiedDate(new Date());
            noteItemMaintainService.update(noteItem);

            // 更新笔记本字段值，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(bookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String getFileName(LongIdKey noteItemKey) {
        return Long.toString(noteItemKey.getLongId());
    }
}
