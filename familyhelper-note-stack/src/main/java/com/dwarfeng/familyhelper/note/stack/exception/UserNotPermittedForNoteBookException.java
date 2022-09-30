package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户对笔记本没有权限异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotPermittedForNoteBookException extends HandlerException {

    private static final long serialVersionUID = -3281527934910767059L;

    private final StringIdKey userKey;
    private final LongIdKey noteBookKey;

    public UserNotPermittedForNoteBookException(StringIdKey userKey, LongIdKey noteBookKey) {
        this.userKey = userKey;
        this.noteBookKey = noteBookKey;
    }

    public UserNotPermittedForNoteBookException(Throwable cause, StringIdKey userKey, LongIdKey noteBookKey) {
        super(cause);
        this.userKey = userKey;
        this.noteBookKey = noteBookKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作笔记本 " + noteBookKey + " 的权限";
    }
}
