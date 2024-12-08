package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookFavoredChangeInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记本收藏变更信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputNoteBookFavoredChangeInfo implements Dto {

    private static final long serialVersionUID = -5514372288679777227L;

    public static NoteBookFavoredChangeInfo toStackBean(WebInputNoteBookFavoredChangeInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NoteBookFavoredChangeInfo(
                    WebInputLongIdKey.toStackBean(webInput.getNoteBookKey())
            );
        }
    }

    @JSONField(name = "note_book_key")
    @NotNull
    @Valid
    private WebInputLongIdKey noteBookKey;

    public WebInputNoteBookFavoredChangeInfo() {
    }

    public WebInputNoteBookFavoredChangeInfo(WebInputLongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public WebInputLongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(WebInputLongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    @Override
    public String toString() {
        return "WebInputNoteBookFavoredChangeInfo{" +
                "noteBookKey=" + noteBookKey +
                '}';
    }
}
