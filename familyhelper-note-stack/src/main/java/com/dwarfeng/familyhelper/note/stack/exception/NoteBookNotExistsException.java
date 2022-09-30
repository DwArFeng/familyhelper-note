package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记本不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookNotExistsException extends HandlerException {

    private static final long serialVersionUID = 2999867877015446937L;

    private final LongIdKey noteBookKey;

    public NoteBookNotExistsException(LongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public NoteBookNotExistsException(Throwable cause, LongIdKey noteBookKey) {
        super(cause);
        this.noteBookKey = noteBookKey;
    }

    @Override
    public String getMessage() {
        return "笔记本 " + noteBookKey + " 不存在";
    }
}
