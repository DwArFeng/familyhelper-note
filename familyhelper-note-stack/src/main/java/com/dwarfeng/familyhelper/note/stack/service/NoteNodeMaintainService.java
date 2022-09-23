package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 笔记节点维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteNodeMaintainService extends BatchCrudService<LongIdKey, NoteNode>,
        EntireLookupService<NoteNode>, PresetLookupService<NoteNode> {

    String CHILD_FOR_PARENT = "child_for_parent";
    String CHILD_FOR_BOOK = "child_for_book";
    String CHILD_FOR_BOOK_ROOT = "child_for_book_root";
    String NAME_LIKE = "name_like";
}
