package com.dwarfeng.familyhelper.note.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 笔记本权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPonbKey implements Key {

    private static final long serialVersionUID = -7333019720175067880L;

    public static FastJsonPonbKey of(PonbKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new FastJsonPonbKey(key.getNoteBookLongId(), key.getUserStringId());
        }
    }

    @JSONField(name = "note_book_long_id", ordinal = 1)
    private Long noteBookLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public FastJsonPonbKey() {
    }

    public FastJsonPonbKey(Long noteBookLongId, String userStringId) {
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
    public String toString() {
        return "FastJsonPonbKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
