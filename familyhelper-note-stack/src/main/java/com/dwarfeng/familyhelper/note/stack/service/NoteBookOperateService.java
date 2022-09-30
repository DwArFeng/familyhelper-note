package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionRemoveInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionUpsertInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 笔记本操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookOperateService extends Service {

    /**
     * 创建笔记本。
     *
     * @param userKey            操作者的主键。
     * @param noteBookCreateInfo 笔记本的创建信息。
     * @return 生成的笔记本的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createNoteBook(StringIdKey userKey, NoteBookCreateInfo noteBookCreateInfo)
            throws ServiceException;

    /**
     * 更新笔记本。
     *
     * @param userKey            操作者的主键。
     * @param noteBookUpdateInfo 笔记本的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo) throws ServiceException;

    /**
     * 删除笔记本。
     *
     * @param userKey     操作者的主键。
     * @param noteBookKey 笔记本的主键。
     * @throws ServiceException 服务异常。
     */
    void removeNoteBook(StringIdKey userKey, LongIdKey noteBookKey) throws ServiceException;

    /**
     * 添加或更新笔记本的访客权限。
     *
     * @param ownerUserKey                 操作者的主键。
     * @param noteBookPermissionUpsertInfo 权限添加信息。
     * @throws ServiceException 服务异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, NoteBookPermissionUpsertInfo noteBookPermissionUpsertInfo)
            throws ServiceException;

    /**
     * 移除笔记本的访客权限。
     *
     * @param ownerUserKey                 操作者的主键。
     * @param noteBookPermissionRemoveInfo 权限移除信息。
     * @throws ServiceException 服务异常。
     */
    void removePermission(StringIdKey ownerUserKey, NoteBookPermissionRemoveInfo noteBookPermissionRemoveInfo)
            throws ServiceException;
}
