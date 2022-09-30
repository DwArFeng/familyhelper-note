package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记节点状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalNoteNodeStateException extends HandlerException {

    private static final long serialVersionUID = -5225842937698537362L;

    private final LongIdKey noteNodeKey;

    public IllegalNoteNodeStateException(LongIdKey noteNodeKey) {
        this.noteNodeKey = noteNodeKey;
    }

    public IllegalNoteNodeStateException(Throwable cause, LongIdKey noteNodeKey) {
        super(cause);
        this.noteNodeKey = noteNodeKey;
    }

    @Override
    public String getMessage() {
        return "笔记节点 " + noteNodeKey + " 状态异常: 它是否没绑定笔记集合?";
    }
}
