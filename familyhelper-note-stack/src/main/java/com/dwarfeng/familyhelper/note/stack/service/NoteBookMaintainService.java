package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 个人最佳集合维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookMaintainService extends BatchCrudService<LongIdKey, NoteBook>,
        EntireLookupService<NoteBook>, PresetLookupService<NoteBook> {

    String NAME_LIKE = "name_like";
}
