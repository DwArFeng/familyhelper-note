package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 笔记项目操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteItemOperateService extends Service {

    /**
     * 创建笔记项目。
     *
     * @param userKey            笔记项目的所有者的主键。
     * @param noteItemCreateInfo 笔记项目的创建信息。
     * @return 生成的笔记项目的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createNoteItem(StringIdKey userKey, NoteItemCreateInfo noteItemCreateInfo) throws ServiceException;

    /**
     * 更新笔记项目。
     *
     * @param userKey            笔记项目的所有者的主键。
     * @param noteItemUpdateInfo 笔记项目的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateNoteItem(StringIdKey userKey, NoteItemUpdateInfo noteItemUpdateInfo) throws ServiceException;

    /**
     * 删除笔记项目。
     *
     * @param userKey     笔记项目的所有者的主键。
     * @param noteItemKey 笔记项目的主键。
     * @throws ServiceException 服务异常。
     */
    void removeNoteItem(StringIdKey userKey, LongIdKey noteItemKey) throws ServiceException;

    /**
     * 下载笔记文件。
     *
     * @param userKey     执行用户主键。
     * @param noteItemKey 笔记项目的注解。
     * @return 笔记文件。
     * @throws ServiceException 服务异常。
     */
    NoteFile downloadNoteFile(StringIdKey userKey, LongIdKey noteItemKey) throws ServiceException;

    /**
     * 上传笔记文件。
     *
     * @param userKey            执行用户主键。
     * @param noteFileUploadInfo 笔记文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadNoteFile(StringIdKey userKey, NoteFileUploadInfo noteFileUploadInfo) throws ServiceException;
}
