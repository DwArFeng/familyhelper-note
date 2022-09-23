package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 笔记本缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookCache extends BatchBaseCache<LongIdKey, NoteBook> {
}
