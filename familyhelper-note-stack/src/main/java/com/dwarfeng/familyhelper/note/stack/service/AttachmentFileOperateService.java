package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUpdateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.AttachmentFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 附件文件操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AttachmentFileOperateService extends Service {

    /**
     * 下载附件文件。
     *
     * @param userKey           执行用户主键。
     * @param attachmentFileKey 附件文件主键。
     * @return 附件文件。
     * @throws ServiceException 服务异常。
     */
    AttachmentFile downloadAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey)
            throws ServiceException;

    /**
     * 上传附件文件。
     *
     * @param userKey                  执行用户主键。
     * @param attachmentFileUploadInfo 附件文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadAttachmentFile(StringIdKey userKey, AttachmentFileUploadInfo attachmentFileUploadInfo)
            throws ServiceException;

    /**
     * 更新附件文件。
     *
     * @param userKey                  执行用户主键。
     * @param attachmentFileUpdateInfo 附件文件更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateAttachmentFile(StringIdKey userKey, AttachmentFileUpdateInfo attachmentFileUpdateInfo)
            throws ServiceException;

    /**
     * 删除附件文件。
     *
     * @param userKey           执行用户主键。
     * @param attachmentFileKey 附件文件主键。
     * @throws ServiceException 服务异常。
     */
    void removeAttachmentFile(StringIdKey userKey, LongIdKey attachmentFileKey) throws ServiceException;
}
