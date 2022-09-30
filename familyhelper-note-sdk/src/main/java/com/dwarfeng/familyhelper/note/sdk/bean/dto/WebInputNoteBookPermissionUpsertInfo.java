package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookPermissionUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 笔记本权限插入或更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBookPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 1894891282389082799L;

    public static NoteBookPermissionUpsertInfo toStackBean(WebInputNoteBookPermissionUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NoteBookPermissionUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getNoteBookKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey()),
                    webInput.getPermissionLevel()
            );
        }
    }

    @JSONField(name = "note_book_key")
    @Valid
    private WebInputLongIdKey noteBookKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    public WebInputNoteBookPermissionUpsertInfo() {
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

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "WebInputNoteBookPermissionUpsertInfo{" +
                "noteBookKey=" + noteBookKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
