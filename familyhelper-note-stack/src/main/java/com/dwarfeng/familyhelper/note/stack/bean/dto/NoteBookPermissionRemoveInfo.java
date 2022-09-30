package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 笔记本权限删除信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -9117463791562451153L;

    private LongIdKey noteBookKey;
    private StringIdKey userKey;

    public NoteBookPermissionRemoveInfo() {
    }

    public NoteBookPermissionRemoveInfo(LongIdKey noteBookKey, StringIdKey userKey) {
        this.noteBookKey = noteBookKey;
        this.userKey = userKey;
    }

    public LongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(LongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "NoteBookPermissionRemoveInfo{" +
                "noteBookKey=" + noteBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
