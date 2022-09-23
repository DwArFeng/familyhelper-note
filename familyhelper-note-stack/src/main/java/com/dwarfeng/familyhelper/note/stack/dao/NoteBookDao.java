package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 笔记本数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookDao extends BatchBaseDao<LongIdKey, NoteBook>, EntireLookupDao<NoteBook>,
        PresetLookupDao<NoteBook> {
}
