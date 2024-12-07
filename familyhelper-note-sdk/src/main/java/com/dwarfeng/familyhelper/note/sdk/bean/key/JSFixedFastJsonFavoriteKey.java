package com.dwarfeng.familyhelper.note.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 收藏主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonFavoriteKey implements Key {

    private static final long serialVersionUID = 2860939808358883181L;

    public static JSFixedFastJsonFavoriteKey of(FavoriteKey favoriteKey) {
        if (Objects.isNull(favoriteKey)) {
            return null;
        } else {
            return new JSFixedFastJsonFavoriteKey(
                    favoriteKey.getNoteBookLongId(),
                    favoriteKey.getUserStringId()
            );
        }
    }

    @JSONField(name = "note_book_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long noteBookLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public JSFixedFastJsonFavoriteKey() {
    }

    public JSFixedFastJsonFavoriteKey(Long noteBookLongId, String userStringId) {
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

        JSFixedFastJsonFavoriteKey that = (JSFixedFastJsonFavoriteKey) o;
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
        return "JSFixedFastJsonFavoriteKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
