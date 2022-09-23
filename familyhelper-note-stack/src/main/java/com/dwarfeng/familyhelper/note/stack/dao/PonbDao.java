package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 笔记本权限数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PonbDao extends BatchBaseDao<PonbKey, Ponb>, EntireLookupDao<Ponb>,
        PresetLookupDao<Ponb> {
}
