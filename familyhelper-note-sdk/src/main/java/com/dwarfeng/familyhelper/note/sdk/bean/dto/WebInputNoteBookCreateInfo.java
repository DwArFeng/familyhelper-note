package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteBookCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记本创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBookCreateInfo implements Dto {

    private static final long serialVersionUID = 4228678579723474906L;

    public static NoteBookCreateInfo toStackBean(WebInputNoteBookCreateInfo webInputNoteBookCreateInfo) {
        if (Objects.isNull(webInputNoteBookCreateInfo)) {
            return null;
        } else {
            return new NoteBookCreateInfo(
                    webInputNoteBookCreateInfo.getName(),
                    webInputNoteBookCreateInfo.getRemark(),
                    webInputNoteBookCreateInfo.isFavorite()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "favorite")
    private boolean favorite;

    public WebInputNoteBookCreateInfo() {
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
        return "WebInputNoteBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
