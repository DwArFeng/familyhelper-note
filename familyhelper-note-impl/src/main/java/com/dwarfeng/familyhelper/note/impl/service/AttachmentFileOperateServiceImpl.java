package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.handler.AttachmentFileOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.AttachmentFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AttachmentFileOperateServiceImpl implements AttachmentFileOperateService {

    private final AttachmentFileOperateHandler attachmentFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public AttachmentFileOperateServiceImpl(
            AttachmentFileOperateHandler attachmentFileOperateHandler, ServiceExceptionMapper sem
    ) {
        this.attachmentFileOperateHandler = attachmentFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public AttachmentFile downloadAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws ServiceException {
        try {
            return attachmentFileOperateHandler.downloadAttachmentFile(userKey, attachmentFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载附件文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadAttachmentFile(StringIdKey userKey, AttachmentFileUploadInfo attachmentFileUploadInfo)
            throws ServiceException {
        try {
            attachmentFileOperateHandler.uploadAttachmentFile(userKey, attachmentFileUploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传附件文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateAttachmentFile(StringIdKey userKey, AttachmentFileUpdateInfo attachmentFileUpdateInfo)
            throws ServiceException {
        try {
            attachmentFileOperateHandler.updateAttachmentFile(userKey, attachmentFileUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新附件文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws ServiceException {
        try {
            attachmentFileOperateHandler.removeAttachmentFile(userKey, attachmentFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除附件文件时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
