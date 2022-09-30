package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记节点创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteNodeCreateInfo implements Dto {

    private static final long serialVersionUID = -1631291893652713563L;

    public static NoteNodeCreateInfo toStackBean(WebInputNoteNodeCreateInfo webInputNoteNodeCreateInfo) {
        if (Objects.isNull(webInputNoteNodeCreateInfo)) {
            return null;
        } else {
            return new NoteNodeCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputNoteNodeCreateInfo.getBookKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteNodeCreateInfo.getParentKey()),
                    webInputNoteNodeCreateInfo.getName(), webInputNoteNodeCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "book_key")
    @Valid
    private WebInputLongIdKey bookKey;

    @JSONField(name = "parent_key")
    @Valid
    private WebInputLongIdKey parentKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputNoteNodeCreateInfo() {
    }

    public WebInputLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(WebInputLongIdKey bookKey) {
        this.bookKey = bookKey;
    }

    public WebInputLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(WebInputLongIdKey parentKey) {
        this.parentKey = parentKey;
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

    @Override
    public String toString() {
        return "WebInputNoteNodeCreateInfo{" +
                "bookKey=" + bookKey +
                ", parentKey=" + parentKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
