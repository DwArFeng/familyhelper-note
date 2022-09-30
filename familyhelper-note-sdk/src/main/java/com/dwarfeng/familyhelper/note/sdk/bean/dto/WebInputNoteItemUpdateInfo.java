package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记项目更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteItemUpdateInfo implements Dto {

    private static final long serialVersionUID = 4202687813871823889L;

    public static NoteItemUpdateInfo toStackBean(WebInputNoteItemUpdateInfo webInputNoteItemUpdateInfo) {
        if (Objects.isNull(webInputNoteItemUpdateInfo)) {
            return null;
        } else {
            return new NoteItemUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputNoteItemUpdateInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteItemUpdateInfo.getNodeKey()),
                    webInputNoteItemUpdateInfo.getName(), webInputNoteItemUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "node_key")
    @Valid
    private WebInputLongIdKey nodeKey;

    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputNoteItemUpdateInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(WebInputLongIdKey nodeKey) {
        this.nodeKey = nodeKey;
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
        return "WebInputNoteItemUpdateInfo{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
