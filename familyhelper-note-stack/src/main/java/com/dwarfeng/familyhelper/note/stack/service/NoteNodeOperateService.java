package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 笔记节点操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteNodeOperateService extends Service {

    /**
     * 创建笔记节点。
     *
     * @param userKey            笔记节点的所有者的主键。
     * @param noteNodeCreateInfo 笔记节点的创建信息。
     * @return 生成的笔记节点的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createNoteNode(StringIdKey userKey, NoteNodeCreateInfo noteNodeCreateInfo) throws ServiceException;

    /**
     * 更新笔记节点。
     *
     * @param userKey            笔记节点的所有者的主键。
     * @param noteNodeUpdateInfo 笔记节点的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateNoteNode(StringIdKey userKey, NoteNodeUpdateInfo noteNodeUpdateInfo) throws ServiceException;

    /**
     * 删除笔记节点。
     *
     * @param userKey     笔记节点的所有者的主键。
     * @param noteNodeKey 笔记节点的主键。
     * @throws ServiceException 服务异常。
     */
    void removeNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws ServiceException;
}
