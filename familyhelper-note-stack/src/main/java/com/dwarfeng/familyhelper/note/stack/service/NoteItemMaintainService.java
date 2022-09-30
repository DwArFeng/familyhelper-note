package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 笔记项目维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteItemMaintainService extends BatchCrudService<LongIdKey, NoteItem>,
        EntireLookupService<NoteItem>, PresetLookupService<NoteItem> {

    String CHILD_FOR_NODE = "child_for_node";
    String CHILD_FOR_BOOK = "child_for_book";
    String CHILD_FOR_BOOK_ROOT = "child_for_book_root";
    String NAME_LIKE = "name_like";
    String CHILD_FOR_NODE_INDEX_ASC = "child_for_node_index_asc";
    String CHILD_FOR_NODE_INDEX_DESC = "child_for_node_index_desc";
}
