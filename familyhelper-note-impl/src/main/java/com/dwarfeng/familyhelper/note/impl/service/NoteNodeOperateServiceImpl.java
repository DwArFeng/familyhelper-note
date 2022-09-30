package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.handler.NoteNodeOperateHandler;
import com.dwarfeng.familyhelper.note.stack.service.NoteNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NoteNodeOperateServiceImpl implements NoteNodeOperateService {

    private final NoteNodeOperateHandler noteNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public NoteNodeOperateServiceImpl(NoteNodeOperateHandler noteNodeOperateHandler, ServiceExceptionMapper sem) {
        this.noteNodeOperateHandler = noteNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createNoteNode(StringIdKey userKey, NoteNodeCreateInfo noteNodeCreateInfo)
            throws ServiceException {
        try {
            return noteNodeOperateHandler.createNoteNode(userKey, noteNodeCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建笔记节点时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateNoteNode(StringIdKey userKey, NoteNodeUpdateInfo noteNodeUpdateInfo)
            throws ServiceException {
        try {
            noteNodeOperateHandler.updateNoteNode(userKey, noteNodeUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新笔记节点时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws ServiceException {
        try {
            noteNodeOperateHandler.removeNoteNode(userKey, noteNodeKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除笔记节点时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
