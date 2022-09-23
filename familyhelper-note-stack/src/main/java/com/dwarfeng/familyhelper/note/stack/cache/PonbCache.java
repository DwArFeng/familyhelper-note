package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 笔记本权限缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PonbCache extends BatchBaseCache<PonbKey, Ponb> {
}
