package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteNode implements Bean {

    private static final long serialVersionUID = 8522168081437482893L;

    public static NoteNode toStackBean(WebInputNoteNode webInputNoteNode) {
        if (Objects.isNull(webInputNoteNode)) {
            return null;
        } else {
            return new NoteNode(
                    WebInputLongIdKey.toStackBean(webInputNoteNode.getKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteNode.getParentKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteNode.getBookKey()),
                    webInputNoteNode.getName(), webInputNoteNode.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "parent_key")
    @Valid
    private WebInputLongIdKey parentKey;

    @JSONField(name = "book_key")
    @Valid
    private WebInputLongIdKey bookKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputNoteNode() {
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

    public WebInputLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(WebInputLongIdKey bookKey) {
        this.bookKey = bookKey;
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
        return "WebInputNoteNode{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", bookKey=" + bookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
