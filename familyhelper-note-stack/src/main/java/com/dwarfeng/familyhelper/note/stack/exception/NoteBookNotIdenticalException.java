package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记笔记本不一致异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookNotIdenticalException extends HandlerException {

    private static final long serialVersionUID = -1273591543858922231L;

    private final LongIdKey parentSetKey;
    private final LongIdKey childSetKey;

    public NoteBookNotIdenticalException(LongIdKey parentSetKey, LongIdKey childSetKey) {
        this.parentSetKey = parentSetKey;
        this.childSetKey = childSetKey;
    }

    public NoteBookNotIdenticalException(
            Throwable cause, LongIdKey parentSetKey, LongIdKey childSetKey
    ) {
        super(cause);
        this.parentSetKey = parentSetKey;
        this.childSetKey = childSetKey;
    }

    @Override
    public String getMessage() {
        return "父项笔记节点所属笔记本 " + parentSetKey + " 与子项笔记节点所属笔记本 " + childSetKey + " 不一致";
    }
}
