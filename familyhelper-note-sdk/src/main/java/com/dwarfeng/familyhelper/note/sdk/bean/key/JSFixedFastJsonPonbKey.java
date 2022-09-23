package com.dwarfeng.familyhelper.note.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 笔记本权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPonbKey implements Key {

    private static final long serialVersionUID = -3833417142930393259L;

    public static JSFixedFastJsonPonbKey of(PonbKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new JSFixedFastJsonPonbKey(key.getNoteBookLongId(), key.getUserStringId());
        }
    }

    @JSONField(name = "note_book_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long noteBookLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public JSFixedFastJsonPonbKey() {
    }

    public JSFixedFastJsonPonbKey(Long noteBookLongId, String userStringId) {
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
        return "JSFixedFastJsonPonbKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
