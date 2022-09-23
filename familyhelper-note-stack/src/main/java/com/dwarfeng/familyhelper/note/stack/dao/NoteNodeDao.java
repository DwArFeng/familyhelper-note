package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 笔记节点数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteNodeDao extends BatchBaseDao<LongIdKey, NoteNode>, EntireLookupDao<NoteNode>,
        PresetLookupDao<NoteNode> {
}
