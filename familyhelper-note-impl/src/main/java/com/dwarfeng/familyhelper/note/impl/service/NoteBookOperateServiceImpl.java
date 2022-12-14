package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.handler.NoteBookOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NoteBookOperateServiceImpl implements NoteBookOperateService {

    private final NoteBookOperateHandler noteBookOperateHandler;

    private final ServiceExceptionMapper sem;

    public NoteBookOperateServiceImpl(
            NoteBookOperateHandler noteBookOperateHandler, ServiceExceptionMapper sem
    ) {
        this.noteBookOperateHandler = noteBookOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createNoteBook(StringIdKey userKey, NoteBookCreateInfo noteBookCreateInfo)
            throws ServiceException {
        try {
            return noteBookOperateHandler.createNoteBook(userKey, noteBookCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建笔记本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo)
            throws ServiceException {
        try {
            noteBookOperateHandler.updateNoteBook(userKey, noteBookUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新笔记本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeNoteBook(StringIdKey userKey, LongIdKey noteBookKey) throws ServiceException {
        try {
            noteBookOperateHandler.removeNoteBook(userKey, noteBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除笔记本时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, NoteBookPermissionUpsertInfo noteBookPermissionUpsertInfo
    ) throws ServiceException {
        try {
            noteBookOperateHandler.upsertPermission(ownerUserKey, noteBookPermissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加笔记本的访客权限时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, NoteBookPermissionRemoveInfo noteBookPermissionRemoveInfo
    ) throws ServiceException {
        try {
            noteBookOperateHandler.removePermission(ownerUserKey, noteBookPermissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("移除笔记本的访客权限时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
