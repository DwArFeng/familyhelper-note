package com.dwarfeng.familyhelper.note.stack.handler;

import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFile;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteFileUploadInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemCreateInfo;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 笔记项目操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NoteItemOperateHandler extends Handler {

    /**
     * 创建笔记项目。
     *
     * @param userKey            笔记项目的所有者的主键。
     * @param noteItemCreateInfo 笔记项目的创建信息。
     * @return 生成的笔记项目的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createNoteItem(StringIdKey userKey, NoteItemCreateInfo noteItemCreateInfo) throws HandlerException;

    /**
     * 更新笔记项目。
     *
     * @param userKey            笔记项目的所有者的主键。
     * @param noteItemUpdateInfo 笔记项目的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateNoteItem(StringIdKey userKey, NoteItemUpdateInfo noteItemUpdateInfo) throws HandlerException;

    /**
     * 删除笔记项目。
     *
     * @param userKey     笔记项目的所有者的主键。
     * @param noteItemKey 笔记项目的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeNoteItem(StringIdKey userKey, LongIdKey noteItemKey) throws HandlerException;

    /**
     * 下载笔记文件。
     *
     * @param userKey     执行用户主键。
     * @param noteItemKey 笔记项目的注解。
     * @return 笔记文件。
     * @throws HandlerException 处理器异常。
     */
    NoteFile downloadNoteFile(StringIdKey userKey, LongIdKey noteItemKey) throws HandlerException;

    /**
     * 上传笔记文件。
     *
     * @param userKey            执行用户主键。
     * @param noteFileUploadInfo 笔记文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadNoteFile(StringIdKey userKey, NoteFileUploadInfo noteFileUploadInfo) throws HandlerException;
}
