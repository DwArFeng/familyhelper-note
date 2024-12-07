package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 收藏维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface FavoriteMaintainService extends BatchCrudService<FavoriteKey, Favorite>,
        EntireLookupService<Favorite>, PresetLookupService<Favorite> {

    String CHILD_FOR_NOTE_BOOK = "child_for_note_book";
    String CHILD_FOR_USER = "child_for_user";
}
