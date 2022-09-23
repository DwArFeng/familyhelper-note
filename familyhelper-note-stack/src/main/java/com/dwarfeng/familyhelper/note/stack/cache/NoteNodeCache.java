package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 笔记节点缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteNodeCache extends BatchBaseCache<LongIdKey, NoteNode> {
}
