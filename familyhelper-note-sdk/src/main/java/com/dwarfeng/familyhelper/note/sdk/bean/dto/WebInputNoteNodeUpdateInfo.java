package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteNodeUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记节点更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteNodeUpdateInfo implements Dto {

    private static final long serialVersionUID = -2992756242709136537L;

    public static NoteNodeUpdateInfo toStackBean(WebInputNoteNodeUpdateInfo webInputNoteNodeUpdateInfo) {
        if (Objects.isNull(webInputNoteNodeUpdateInfo)) {
            return null;
        } else {
            return new NoteNodeUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputNoteNodeUpdateInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteNodeUpdateInfo.getParentKey()),
                    webInputNoteNodeUpdateInfo.getName(), webInputNoteNodeUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "set_key")
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

    public WebInputNoteNodeUpdateInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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
        return "WebInputNoteNodeUpdateInfo{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
