package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 笔记本维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookMaintainService extends BatchCrudService<LongIdKey, NoteBook>,
        EntireLookupService<NoteBook>, PresetLookupService<NoteBook> {

    String NAME_LIKE = "name_like";

    /**
     * 获取笔记本。
     *
     * <p>
     * 返回指定用户拥有的笔记本。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     * </ol>
     *
     * @since 1.1.0
     */
    String USER_OWNED = "user_owned";

    /**
     * 获取展示用的笔记本。
     *
     * <p>
     * 返回指定用户有权限（任何权限均可）的笔记本，且满足查询条件。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     *     <li>String 笔记本名称的模糊匹配字符串，如果值为空（blank)，则不参与查询。</li>
     *     <li>boolean 是否只查询收藏的笔记本。</li>
     * </ol>
     * 返回的数据按照名称升序排列。
     *
     * @since 1.1.0
     */
    String USER_PERMITTED_WITH_CONDITION_DISPLAY = "user_permitted_with_condition_display";

    /**
     * 获取展示用的笔记本。
     *
     * <p>
     * 返回指定用户拥有的笔记本，且满足查询条件。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     *     <li>String 笔记本名称的模糊匹配字符串，如果值为空（blank)，则不参与查询。</li>
     *     <li>boolean 是否只查询收藏的笔记本。</li>
     * </ol>
     * 返回的数据按照名称升序排列。
     *
     * @since 1.1.0
     */
    String USER_OWNED_WITH_CONDITION_DISPLAY = "user_owned_name_like_display";
}
