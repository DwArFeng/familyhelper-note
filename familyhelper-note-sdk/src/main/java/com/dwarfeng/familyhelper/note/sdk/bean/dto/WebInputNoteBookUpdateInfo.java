package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记本更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBookUpdateInfo implements Dto {

    private static final long serialVersionUID = 4375909168066912677L;

    public static NoteBookUpdateInfo toStackBean(WebInputNoteBookUpdateInfo webInputNoteBookUpdateInfo) {
        if (Objects.isNull(webInputNoteBookUpdateInfo)) {
            return null;
        } else {
            return new NoteBookUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputNoteBookUpdateInfo.getNoteBookKey()),
                    webInputNoteBookUpdateInfo.getName(),
                    webInputNoteBookUpdateInfo.getRemark(),
                    webInputNoteBookUpdateInfo.isFavorite()
            );
        }
    }

    @JSONField(name = "note_book_key")
    @Valid
    @NotNull
    private WebInputLongIdKey noteBookKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "favorite")
    private boolean favorite;

    public WebInputNoteBookUpdateInfo() {
    }

    public WebInputLongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(WebInputLongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "WebInputNoteBookUpdateInfo{" +
                "noteBookKey=" + noteBookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
