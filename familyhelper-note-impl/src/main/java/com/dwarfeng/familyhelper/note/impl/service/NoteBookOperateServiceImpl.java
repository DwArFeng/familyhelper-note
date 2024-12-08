package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.*;
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
            throw ServiceExceptionHelper.logParse("创建笔记本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo)
            throws ServiceException {
        try {
            noteBookOperateHandler.updateNoteBook(userKey, noteBookUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新笔记本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeNoteBook(StringIdKey userKey, LongIdKey noteBookKey) throws ServiceException {
        try {
            noteBookOperateHandler.removeNoteBook(userKey, noteBookKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除笔记本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, NoteBookPermissionUpsertInfo noteBookPermissionUpsertInfo
    ) throws ServiceException {
        try {
            noteBookOperateHandler.upsertPermission(ownerUserKey, noteBookPermissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("添加笔记本的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, NoteBookPermissionRemoveInfo noteBookPermissionRemoveInfo
    ) throws ServiceException {
        try {
            noteBookOperateHandler.removePermission(ownerUserKey, noteBookPermissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除笔记本的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeFavored(StringIdKey operateUserKey, NoteBookFavoredChangeInfo info) throws ServiceException {
        try {
            noteBookOperateHandler.changeFavored(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变笔记本的收藏状态时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
