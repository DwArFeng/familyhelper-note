package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记项目状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalNoteItemStateException extends HandlerException {

    private static final long serialVersionUID = 397927344821650315L;

    private final LongIdKey noteItemKey;

    public IllegalNoteItemStateException(LongIdKey noteItemKey) {
        this.noteItemKey = noteItemKey;
    }

    public IllegalNoteItemStateException(Throwable cause, LongIdKey noteItemKey) {
        super(cause);
        this.noteItemKey = noteItemKey;
    }

    @Override
    public String getMessage() {
        return "笔记项目 " + noteItemKey + " 状态异常: 它是否没绑定笔记集合?";
    }
}
