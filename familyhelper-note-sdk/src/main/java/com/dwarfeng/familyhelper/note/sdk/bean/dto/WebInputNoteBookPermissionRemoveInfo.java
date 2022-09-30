package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 笔记本权限删除信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBookPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = 3777684208083118591L;

    public static NoteBookPermissionRemoveInfo toStackBean(WebInputNoteBookPermissionRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NoteBookPermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getNoteBookKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey())
            );
        }
    }

    @JSONField(name = "note_book_key")
    @Valid
    private WebInputLongIdKey noteBookKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputNoteBookPermissionRemoveInfo() {
    }

    public WebInputLongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(WebInputLongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "WebInputNoteBookPermissionRemoveInfo{" +
                "noteBookKey=" + noteBookKey +
                ", userKey=" + userKey +
                '}';
    }
}
