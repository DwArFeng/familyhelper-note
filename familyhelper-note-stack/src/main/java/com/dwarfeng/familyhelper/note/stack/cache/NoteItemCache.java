package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 笔记项目缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteItemCache extends BatchBaseCache<LongIdKey, NoteItem> {
}
