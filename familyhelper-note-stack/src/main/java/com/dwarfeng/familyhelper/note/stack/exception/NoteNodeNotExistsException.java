package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记节点不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteNodeNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8999751751903447743L;

    private final LongIdKey noteNodeKey;

    public NoteNodeNotExistsException(LongIdKey noteNodeKey) {
        this.noteNodeKey = noteNodeKey;
    }

    public NoteNodeNotExistsException(Throwable cause, LongIdKey noteNodeKey) {
        super(cause);
        this.noteNodeKey = noteNodeKey;
    }

    @Override
    public String getMessage() {
        return "笔记节点 " + noteNodeKey + " 不存在";
    }
}
