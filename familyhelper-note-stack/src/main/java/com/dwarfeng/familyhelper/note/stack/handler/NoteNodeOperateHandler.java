package com.dwarfeng.familyhelper.note.stack.handler;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 笔记节点操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteNodeOperateHandler extends Handler {

    /**
     * 创建笔记节点。
     *
     * @param userKey            笔记节点的所有者的主键。
     * @param noteNodeCreateInfo 笔记节点的创建信息。
     * @return 生成的笔记节点的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createNoteNode(StringIdKey userKey, NoteNodeCreateInfo noteNodeCreateInfo) throws HandlerException;

    /**
     * 更新笔记节点。
     *
     * @param userKey            笔记节点的所有者的主键。
     * @param noteNodeUpdateInfo 笔记节点的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateNoteNode(StringIdKey userKey, NoteNodeUpdateInfo noteNodeUpdateInfo) throws HandlerException;

    /**
     * 删除笔记节点。
     *
     * @param userKey     笔记节点的所有者的主键。
     * @param noteNodeKey 笔记节点的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws HandlerException;
}
