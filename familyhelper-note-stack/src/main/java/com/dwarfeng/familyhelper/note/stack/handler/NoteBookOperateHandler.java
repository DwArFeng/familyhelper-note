package com.dwarfeng.familyhelper.note.stack.handler;

import com.dwarfeng.familyhelper.note.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 笔记本操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteBookOperateHandler extends Handler {

    /**
     * 创建笔记本。
     *
     * @param userKey            操作者的主键。
     * @param noteBookCreateInfo 笔记本的创建信息。
     * @return 生成的笔记本的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createNoteBook(StringIdKey userKey, NoteBookCreateInfo noteBookCreateInfo)
            throws HandlerException;

    /**
     * 更新笔记本。
     *
     * @param userKey            操作者的主键。
     * @param noteBookUpdateInfo 笔记本的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateNoteBook(StringIdKey userKey, NoteBookUpdateInfo noteBookUpdateInfo) throws HandlerException;

    /**
     * 删除笔记本。
     *
     * @param userKey     操作者的主键。
     * @param noteBookKey 笔记本的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeNoteBook(StringIdKey userKey, LongIdKey noteBookKey) throws HandlerException;

    /**
     * 添加或更新笔记本的访客权限。
     *
     * @param ownerUserKey                 操作者的主键。
     * @param noteBookPermissionUpsertInfo 权限添加信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, NoteBookPermissionUpsertInfo noteBookPermissionUpsertInfo)
            throws HandlerException;

    /**
     * 移除笔记本的访客权限。
     *
     * @param ownerUserKey                 操作者的主键。
     * @param noteBookPermissionRemoveInfo 权限移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removePermission(StringIdKey ownerUserKey, NoteBookPermissionRemoveInfo noteBookPermissionRemoveInfo)
            throws HandlerException;

    /**
     * 改变笔记本的收藏状态。
     *
     * @param operateUserKey 操作者的主键。
     * @param info           笔记本收藏变更信息。
     * @throws HandlerException 处理器异常。
     */
    void changeFavored(StringIdKey operateUserKey, NoteBookFavoredChangeInfo info) throws HandlerException;
}
