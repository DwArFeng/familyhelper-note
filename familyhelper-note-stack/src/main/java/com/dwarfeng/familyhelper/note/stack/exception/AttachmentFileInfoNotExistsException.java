package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 附件文件信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AttachmentFileInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -2658712231886153462L;

    private final LongIdKey attachmentFileInfoKey;

    public AttachmentFileInfoNotExistsException(LongIdKey attachmentFileInfoKey) {
        this.attachmentFileInfoKey = attachmentFileInfoKey;
    }

    public AttachmentFileInfoNotExistsException(Throwable cause, LongIdKey attachmentFileInfoKey) {
        super(cause);
        this.attachmentFileInfoKey = attachmentFileInfoKey;
    }

    @Override
    public String getMessage() {
        return "附件文件信息 " + attachmentFileInfoKey + " 不存在";
    }
}
