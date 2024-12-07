package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.handler.AttachmentFileOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.AttachmentFileInfoMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AttachmentFileOperateHandlerImpl implements AttachmentFileOperateHandler {

    private final AttachmentFileInfoMaintainService attachmentFileInfoMaintainService;
    private final NoteBookMaintainService noteBookMaintainService;
    private final NoteItemMaintainService noteItemMaintainService;

    private final FtpHandler ftpHandler;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final OperateHandlerValidator operateHandlerValidator;

    public AttachmentFileOperateHandlerImpl(
            AttachmentFileInfoMaintainService attachmentFileInfoMaintainService,
            NoteBookMaintainService noteBookMaintainService,
            NoteItemMaintainService noteItemMaintainService,
            FtpHandler ftpHandler,
            KeyGenerator<LongIdKey> keyGenerator,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.attachmentFileInfoMaintainService = attachmentFileInfoMaintainService;
        this.noteBookMaintainService = noteBookMaintainService;
        this.noteItemMaintainService = noteItemMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyGenerator = keyGenerator;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public AttachmentFile downloadAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey)
            throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认附件文件存在。
            operateHandlerValidator.makeSureAttachmentFileInfoExists(attachmentFileKey);

            // 确认笔记项目存在。
            AttachmentFileInfo attachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileKey);
            LongIdKey noteItemKey = attachmentFileInfo.getNoteItemKey();
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);

            // 确认笔记本存在。
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            LongIdKey noteBookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 获取附件文件对应的项目，并确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserInspectPermittedForAttachmentFileInfo(userKey, attachmentFileKey);

            // 下载附件文件。
            byte[] content = ftpHandler.retrieveFile(
                    FtpConstants.PATH_ATTACHMENT_FILE, getFileName(attachmentFileKey)
            );

            // 更新文件的查看时间。
            Date currentDate = new Date();
            attachmentFileInfo.setInspectedDate(currentDate);
            attachmentFileInfoMaintainService.update(attachmentFileInfo);

            // 更新笔记项目的查看时间，并调用维护服务进行更新。
            noteItem.setInspectedDate(currentDate);
            noteItemMaintainService.update(noteItem);

            // 更新笔记本的查看时间，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(noteBookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBookMaintainService.update(noteBook);

            // 拼接 AttachmentFile 并返回。
            return new AttachmentFile(attachmentFileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadAttachmentFile(StringIdKey userKey, AttachmentFileUploadInfo attachmentFileUploadInfo)
            throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认笔记项目存在。
            LongIdKey noteItemKey = attachmentFileUploadInfo.getNoteItemKey();
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);

            // 确认笔记本存在。
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            LongIdKey noteBookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForNoteItem(userKey, noteItemKey);

            // 分配主键。
            LongIdKey attachmentFileKey = keyGenerator.generate();

            // 附件文件内容并存储（覆盖）。
            byte[] content = attachmentFileUploadInfo.getContent();
            ftpHandler.storeFile(
                    FtpConstants.PATH_ATTACHMENT_FILE, getFileName(attachmentFileKey), content
            );

            // 根据 attachmentFileUploadInfo 构造 AttachmentFileInfo，插入或更新。
            Date currentDate = new Date();
            AttachmentFileInfo attachmentFileInfo = new AttachmentFileInfo();
            attachmentFileInfo.setKey(attachmentFileKey);
            attachmentFileInfo.setNoteItemKey(noteItemKey);
            attachmentFileInfo.setOriginName(attachmentFileUploadInfo.getOriginName());
            attachmentFileInfo.setLength(attachmentFileUploadInfo.getContent().length);
            attachmentFileInfo.setCreatedDate(currentDate);
            attachmentFileInfo.setInspectedDate(currentDate);
            attachmentFileInfo.setModifiedDate(currentDate);
            attachmentFileInfo.setRemark("通过 familyhelper-note 服务上传/更新附件文件");
            attachmentFileInfoMaintainService.insertOrUpdate(attachmentFileInfo);

            // 更新笔记项目的属性，并调用维护服务进行更新。
            noteItem.setInspectedDate(currentDate);
            noteItem.setModifiedDate(currentDate);
            noteItemMaintainService.update(noteItem);

            // 更新笔记本的属性，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(noteBookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);

        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void updateAttachmentFile(StringIdKey userKey, AttachmentFileUpdateInfo attachmentFileUpdateInfo)
            throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认附件文件信息存在。
            LongIdKey attachmentFileInfoKey = attachmentFileUpdateInfo.getAttachmentFileInfoKey();
            operateHandlerValidator.makeSureAttachmentFileInfoExists(attachmentFileInfoKey);

            // 确认笔记项目存在。
            AttachmentFileInfo attachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileInfoKey);
            LongIdKey noteItemKey = attachmentFileInfo.getNoteItemKey();
            operateHandlerValidator.makeSureNoteItemExists(noteItemKey);

            // 确认笔记本存在。
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            LongIdKey noteBookKey = noteItem.getBookKey();
            operateHandlerValidator.makeSureNoteBookExists(noteBookKey);

            // 确认用户有权限操作附件文件信息。
            operateHandlerValidator.makeSureUserModifyPermittedForAttachmentFileInfo(userKey, attachmentFileInfoKey);

            // 附件文件内容并存储（覆盖）。
            byte[] content = attachmentFileUpdateInfo.getContent();
            ftpHandler.storeFile(
                    FtpConstants.PATH_ATTACHMENT_FILE, getFileName(attachmentFileInfoKey), content
            );

            // 根据 attachmentFileUpdateInfo 更新字段。
            Date currentDate = new Date();
            attachmentFileInfo.setOriginName(attachmentFileUpdateInfo.getOriginName());
            attachmentFileInfo.setLength(content.length);
            attachmentFileInfo.setInspectedDate(new Date());
            attachmentFileInfo.setModifiedDate(new Date());
            attachmentFileInfoMaintainService.update(attachmentFileInfo);

            // 更新笔记项目的属性，并调用维护服务进行更新。
            noteItem.setInspectedDate(currentDate);
            noteItem.setModifiedDate(currentDate);
            noteItemMaintainService.update(noteItem);

            // 更新笔记本的属性，并调用维护服务进行更新。
            NoteBook noteBook = noteBookMaintainService.get(noteBookKey);
            noteBook.setLastInspectedDate(currentDate);
            noteBook.setLastModifiedDate(currentDate);
            noteBookMaintainService.update(noteBook);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认附件文件存在。
            operateHandlerValidator.makeSureAttachmentFileInfoExists(attachmentFileKey);

            // 获取附件文件对应的项目，并确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForAttachmentFileInfo(userKey, attachmentFileKey);

            // 如果存在 AttachmentFile 文件，则删除。
            if (ftpHandler.existsFile(
                    FtpConstants.PATH_ATTACHMENT_FILE, getFileName(attachmentFileKey)
            )) {
                ftpHandler.deleteFile(FtpConstants.PATH_ATTACHMENT_FILE, getFileName(attachmentFileKey));
            }

            // 如果存在 AttachmentFileInfo 实体，则删除。
            attachmentFileInfoMaintainService.deleteIfExists(attachmentFileKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private String getFileName(LongIdKey attachmentFileKey) {
        return Long.toString(attachmentFileKey.getLongId());
    }
}
