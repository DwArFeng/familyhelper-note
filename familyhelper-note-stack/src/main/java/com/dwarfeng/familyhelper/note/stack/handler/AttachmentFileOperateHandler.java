package com.dwarfeng.familyhelper.note.stack.handler;

import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 附件文件操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AttachmentFileOperateHandler extends Handler {

    /**
     * 下载附件文件。
     *
     * @param userKey           执行用户主键。
     * @param attachmentFileKey 附件文件主键。
     * @return 附件文件。
     * @throws HandlerException 处理器异常。
     */
    AttachmentFile downloadAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws HandlerException;

    /**
     * 上传附件文件。
     *
     * @param userKey                  执行用户主键。
     * @param attachmentFileUploadInfo 附件文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadAttachmentFile(StringIdKey userKey, AttachmentFileUploadInfo attachmentFileUploadInfo)
            throws HandlerException;

    /**
     * 更新附件文件。
     *
     * @param userKey                  执行用户主键。
     * @param attachmentFileUpdateInfo 附件文件更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateAttachmentFile(StringIdKey userKey, AttachmentFileUpdateInfo attachmentFileUpdateInfo)
            throws HandlerException;

    /**
     * 删除附件文件。
     *
     * @param userKey           执行用户主键。
     * @param attachmentFileKey 附件文件主键。
     * @throws HandlerException 处理器异常。
     */
    void removeAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws HandlerException;
}
