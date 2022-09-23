package com.dwarfeng.familyhelper.note.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 笔记本权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class HibernatePonbKey implements Key {

    private static final long serialVersionUID = -7683077517424675549L;

    private Long noteBookLongId;
    private String userStringId;

    public HibernatePonbKey() {
    }

    public HibernatePonbKey(Long noteBookLongId, String userStringId) {
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

        HibernatePonbKey that = (HibernatePonbKey) o;

        if (!Objects.equals(noteBookLongId, that.noteBookLongId))
            return false;
        return Objects.equals(userStringId, that.userStringId);
    }

    @Override
    public int hashCode() {
        int result = noteBookLongId != null ? noteBookLongId.hashCode() : 0;
        result = 31 * result + (userStringId != null ? userStringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernatePonbKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
