package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 笔记本权限维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PonbMaintainService extends BatchCrudService<PonbKey, Ponb>,
        EntireLookupService<Ponb>, PresetLookupService<Ponb> {

    String CHILD_FOR_NOTE_BOOK = "child_for_note_book";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_NOTE_BOOK_PERMISSION_LEVEL_EQUALS = "child_for_note_book_permission_level_equals";
    String CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS = "child_for_user_permission_level_equals";
}
