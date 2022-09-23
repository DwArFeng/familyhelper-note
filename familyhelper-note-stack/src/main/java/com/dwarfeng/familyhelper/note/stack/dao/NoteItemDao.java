package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 笔记项目数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteItemDao extends BatchBaseDao<LongIdKey, NoteItem>, EntireLookupDao<NoteItem>,
        PresetLookupDao<NoteItem> {
}
