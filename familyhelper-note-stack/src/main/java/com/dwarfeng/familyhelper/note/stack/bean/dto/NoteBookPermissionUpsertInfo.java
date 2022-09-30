package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 笔记本权限插入或更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 5018871585433741217L;

    private LongIdKey noteBookKey;
    private StringIdKey userKey;
    private int permissionLevel;

    public NoteBookPermissionUpsertInfo() {
    }

    public NoteBookPermissionUpsertInfo(LongIdKey NoteBookKey, StringIdKey userKey, int permissionLevel) {
        this.noteBookKey = NoteBookKey;
        this.userKey = userKey;
        this.permissionLevel = permissionLevel;
    }

    public LongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(LongIdKey NoteBookKey) {
        this.noteBookKey = NoteBookKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "NoteBookPermissionUpsertInfo{" +
                "noteBookKey=" + noteBookKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
