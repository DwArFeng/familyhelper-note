package com.dwarfeng.familyhelper.note.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记本权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPonbKey implements Key {

    private static final long serialVersionUID = 164656174519875891L;

    public static PonbKey toStackBean(WebInputPonbKey webInputPonbKey) {
        if (Objects.isNull(webInputPonbKey)) {
            return null;
        } else {
            return new PonbKey(webInputPonbKey.getNoteBookLongId(), webInputPonbKey.getUserStringId());
        }
    }

    @JSONField(name = "note_book_long_id")
    @NotNull
    private Long noteBookLongId;

    @JSONField(name = "user_string_id")
    @NotNull
    @NotEmpty
    private String userStringId;

    public WebInputPonbKey() {
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
        return "WebInputPonbKey{" +
                "noteBookLongId=" + noteBookLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
