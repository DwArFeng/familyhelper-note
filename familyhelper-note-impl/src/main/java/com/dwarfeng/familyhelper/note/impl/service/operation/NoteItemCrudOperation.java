package com.dwarfeng.familyhelper.note.impl.service.operation;

import com.dwarfeng.familyhelper.note.impl.handler.FtpPathResolver;
import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.cache.NoteItemCache;
import com.dwarfeng.familyhelper.note.stack.dao.AttachmentFileInfoDao;
import com.dwarfeng.familyhelper.note.stack.dao.NoteItemDao;
import com.dwarfeng.familyhelper.note.stack.service.AttachmentFileInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteItemCrudOperation implements BatchCrudOperation<LongIdKey, NoteItem> {

    private final NoteItemDao noteItemDao;
    private final NoteItemCache noteItemCache;

    private final AttachmentFileInfoCrudOperation attachmentFileInfoCrudOperation;
    private final AttachmentFileInfoDao attachmentFileInfoDao;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.note_item}")
    private long noteItemTimeout;

    public NoteItemCrudOperation(
            NoteItemDao noteItemDao,
            NoteItemCache noteItemCache,
            AttachmentFileInfoCrudOperation attachmentFileInfoCrudOperation,
            AttachmentFileInfoDao attachmentFileInfoDao,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.noteItemDao = noteItemDao;
        this.noteItemCache = noteItemCache;
        this.attachmentFileInfoCrudOperation = attachmentFileInfoCrudOperation;
        this.attachmentFileInfoDao = attachmentFileInfoDao;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return noteItemCache.exists(key) || noteItemDao.exists(key);
    }

    @Override
    public NoteItem get(LongIdKey key) throws Exception {
        if (noteItemCache.exists(key)) {
            return noteItemCache.get(key);
        } else {
            if (!noteItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NoteItem noteItem = noteItemDao.get(key);
            noteItemCache.push(noteItem, noteItemTimeout);
            return noteItem;
        }
    }

    @Override
    public LongIdKey insert(NoteItem noteItem) throws Exception {
        noteItemCache.push(noteItem, noteItemTimeout);
        return noteItemDao.insert(noteItem);
    }

    @Override
    public void update(NoteItem noteItem) throws Exception {
        noteItemCache.push(noteItem, noteItemTimeout);
        noteItemDao.update(noteItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在笔记文件，则删除笔记文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_NOTE_FILE), getFileName(key)
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_NOTE_FILE), getFileName(key)
            );
        }

        // 查找删除除所有相关的笔记记录。
        List<LongIdKey> attachmentFileInfoKeys = attachmentFileInfoDao.lookup(
                AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM, new Object[]{key}
        ).stream().map(AttachmentFileInfo::getKey).collect(Collectors.toList());
        attachmentFileInfoCrudOperation.batchDelete(attachmentFileInfoKeys);

        // 删除笔记项目自身。
        noteItemCache.delete(key);
        noteItemDao.delete(key);
    }

    private String getFileName(LongIdKey noteFileKey) {
        return Long.toString(noteFileKey.getLongId());
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return noteItemCache.allExists(keys) || noteItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return noteItemCache.nonExists(keys) && noteItemDao.nonExists(keys);
    }

    @Override
    public List<NoteItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (noteItemCache.allExists(keys)) {
            return noteItemCache.batchGet(keys);
        } else {
            if (!noteItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NoteItem> noteItems = noteItemDao.batchGet(keys);
            noteItemCache.batchPush(noteItems, noteItemTimeout);
            return noteItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NoteItem> noteItems) throws Exception {
        noteItemCache.batchPush(noteItems, noteItemTimeout);
        return noteItemDao.batchInsert(noteItems);
    }

    @Override
    public void batchUpdate(List<NoteItem> noteItems) throws Exception {
        noteItemCache.batchPush(noteItems, noteItemTimeout);
        noteItemDao.batchUpdate(noteItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
