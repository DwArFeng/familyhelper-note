package com.dwarfeng.familyhelper.note.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 附件文件信息状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalAttachmentFileInfoStateException extends HandlerException {

    private static final long serialVersionUID = -5875883188636099152L;

    private final LongIdKey attachmentFileInfoKey;

    public IllegalAttachmentFileInfoStateException(LongIdKey attachmentFileInfoKey) {
        this.attachmentFileInfoKey = attachmentFileInfoKey;
    }

    public IllegalAttachmentFileInfoStateException(Throwable cause, LongIdKey attachmentFileInfoKey) {
        super(cause);
        this.attachmentFileInfoKey = attachmentFileInfoKey;
    }

    @Override
    public String getMessage() {
        return "附件文件信息 " + attachmentFileInfoKey + " 状态异常: 它是否没绑定个人最佳项目?";
    }
}
