package com.dwarfeng.familyhelper.note.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 收藏主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateFavoriteKey implements Key {

    private static final long serialVersionUID = -4483243693270078293L;

    private Long noteBookLongId;
    private String userStringId;

    public HibernateFavoriteKey() {
    }

    public HibernateFavoriteKey(Long noteBookLongId, String userStringId) {
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
        if (o == null || getClass() != o.getClass()) return false;

        HibernateFavoriteKey that = (HibernateFavoriteKey) o;
        return Objects.equals(noteBookLongId, that.noteBookLongId) && Objects.equals(userStringId, that.userStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(noteBookLongId);
        result = 31 * result + Objects.hashCode(userStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateFavoriteKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
