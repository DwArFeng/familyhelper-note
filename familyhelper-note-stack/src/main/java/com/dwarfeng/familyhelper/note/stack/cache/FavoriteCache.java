package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 收藏缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface FavoriteCache extends BatchBaseCache<FavoriteKey, Favorite> {
}
