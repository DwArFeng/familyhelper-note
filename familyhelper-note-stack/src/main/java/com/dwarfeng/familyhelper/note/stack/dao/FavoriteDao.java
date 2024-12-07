package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 收藏数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface FavoriteDao extends BatchBaseDao<FavoriteKey, Favorite>, EntireLookupDao<Favorite>,
        PresetLookupDao<Favorite> {
}
