package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记项目不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteItemNotExistsException extends HandlerException {

    private static final long serialVersionUID = -7483468914286270630L;

    private final LongIdKey noteItemKey;

    public NoteItemNotExistsException(LongIdKey noteItemKey) {
        this.noteItemKey = noteItemKey;
    }

    public NoteItemNotExistsException(Throwable cause, LongIdKey noteItemKey) {
        super(cause);
        this.noteItemKey = noteItemKey;
    }

    @Override
    public String getMessage() {
        return "笔记项目 " + noteItemKey + " 不存在";
    }
}
