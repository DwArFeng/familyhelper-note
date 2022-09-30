package com.dwarfeng.familyhelper.note.impl.service.operation;

import com.dwarfeng.familyhelper.note.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.familyhelper.note.stack.cache.AttachmentFileInfoCache;
import com.dwarfeng.familyhelper.note.stack.dao.AttachmentFileInfoDao;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttachmentFileInfoCrudOperation implements BatchCrudOperation<LongIdKey, AttachmentFileInfo> {

    private final AttachmentFileInfoDao attachmentFileInfoDao;
    private final AttachmentFileInfoCache attachmentFileInfoCache;

    private final FtpHandler ftpHandler;

    @Value("${cache.timeout.entity.attachment_file_info}")
    private long attachmentFileInfoTimeout;

    public AttachmentFileInfoCrudOperation(
            AttachmentFileInfoDao attachmentFileInfoDao, AttachmentFileInfoCache attachmentFileInfoCache,
            FtpHandler ftpHandler
    ) {
        this.attachmentFileInfoDao = attachmentFileInfoDao;
        this.attachmentFileInfoCache = attachmentFileInfoCache;
        this.ftpHandler = ftpHandler;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return attachmentFileInfoCache.exists(key) || attachmentFileInfoDao.exists(key);
    }

    @Override
    public AttachmentFileInfo get(LongIdKey key) throws Exception {
        if (attachmentFileInfoCache.exists(key)) {
            return attachmentFileInfoCache.get(key);
        } else {
            if (!attachmentFileInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AttachmentFileInfo attachmentFileInfo = attachmentFileInfoDao.get(key);
            attachmentFileInfoCache.push(attachmentFileInfo, attachmentFileInfoTimeout);
            return attachmentFileInfo;
        }
    }

    @Override
    public LongIdKey insert(AttachmentFileInfo attachmentFileInfo) throws Exception {
        attachmentFileInfoCache.push(attachmentFileInfo, attachmentFileInfoTimeout);
        return attachmentFileInfoDao.insert(attachmentFileInfo);
    }

    @Override
    public void update(AttachmentFileInfo attachmentFileInfo) throws Exception {
        attachmentFileInfoCache.push(attachmentFileInfo, attachmentFileInfoTimeout);
        attachmentFileInfoDao.update(attachmentFileInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在附件文件，则删除附件文件。
        if (ftpHandler.existsFile(new String[]{FtpConstants.PATH_ATTACHMENT_FILE}, getFileName(key))) {
            ftpHandler.deleteFile(new String[]{FtpConstants.PATH_ATTACHMENT_FILE}, getFileName(key));
        }

        // 删除附件文件信息实体自身。
        attachmentFileInfoCache.delete(key);
        attachmentFileInfoDao.delete(key);
    }

    private String getFileName(LongIdKey attachmentFileKey) {
        return Long.toString(attachmentFileKey.getLongId());
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return attachmentFileInfoCache.allExists(keys) || attachmentFileInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return attachmentFileInfoCache.nonExists(keys) && attachmentFileInfoDao.nonExists(keys);
    }

    @Override
    public List<AttachmentFileInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (attachmentFileInfoCache.allExists(keys)) {
            return attachmentFileInfoCache.batchGet(keys);
        } else {
            if (!attachmentFileInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AttachmentFileInfo> attachmentFileInfos = attachmentFileInfoDao.batchGet(keys);
            attachmentFileInfoCache.batchPush(attachmentFileInfos, attachmentFileInfoTimeout);
            return attachmentFileInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AttachmentFileInfo> attachmentFileInfos) throws Exception {
        attachmentFileInfoCache.batchPush(attachmentFileInfos, attachmentFileInfoTimeout);
        return attachmentFileInfoDao.batchInsert(attachmentFileInfos);
    }

    @Override
    public void batchUpdate(List<AttachmentFileInfo> attachmentFileInfos) throws Exception {
        attachmentFileInfoCache.batchPush(attachmentFileInfos, attachmentFileInfoTimeout);
        attachmentFileInfoDao.batchUpdate(attachmentFileInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
