package com.dwarfeng.familyhelper.note.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 笔记本权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PonbKey implements Key {

    private static final long serialVersionUID = 887957669252288193L;

    private Long noteBookLongId;
    private String userStringId;

    public PonbKey() {
    }

    public PonbKey(Long noteBookLongId, String userStringId) {
        this.noteBookLongId = noteBookLongId;
        this.userStringId = userStringId;
    }

    public Long getNoteBookLongId() {
        return noteBookLongId;
    }

    public void setNoteBookLongId(Long noteBookLongId) {
        this.noteBookLongId = noteBookLongId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PonbKey ponbKey = (PonbKey) o;

        if (!Objects.equals(noteBookLongId, ponbKey.noteBookLongId)) return false;
        return Objects.equals(userStringId, ponbKey.userStringId);
    }

    @Override
    public int hashCode() {
        int result = noteBookLongId != null ? noteBookLongId.hashCode() : 0;
        result = 31 * result + (userStringId != null ? userStringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PonbKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
