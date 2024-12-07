package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.handler.NoteItemOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NoteItemOperateServiceImpl implements NoteItemOperateService {

    private final NoteItemOperateHandler noteItemOperateHandler;

    private final ServiceExceptionMapper sem;

    public NoteItemOperateServiceImpl(NoteItemOperateHandler noteItemOperateHandler, ServiceExceptionMapper sem) {
        this.noteItemOperateHandler = noteItemOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createNoteItem(StringIdKey userKey, NoteItemCreateInfo noteItemCreateInfo)
            throws ServiceException {
        try {
            return noteItemOperateHandler.createNoteItem(userKey, noteItemCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建笔记项目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateNoteItem(StringIdKey userKey, NoteItemUpdateInfo noteItemUpdateInfo)
            throws ServiceException {
        try {
            noteItemOperateHandler.updateNoteItem(userKey, noteItemUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新笔记项目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeNoteItem(StringIdKey userKey, LongIdKey noteItemKey) throws ServiceException {
        try {
            noteItemOperateHandler.removeNoteItem(userKey, noteItemKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除笔记项目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public NoteFile downloadNoteFile(StringIdKey userKey, LongIdKey noteItemKey) throws ServiceException {
        try {
            return noteItemOperateHandler.downloadNoteFile(userKey, noteItemKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载笔记文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadNoteFile(StringIdKey userKey, NoteFileUploadInfo noteFileUploadInfo) throws ServiceException {
        try {
            noteItemOperateHandler.uploadNoteFile(userKey, noteFileUploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传笔记文件时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
