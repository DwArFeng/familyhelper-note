package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 笔记项目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteItem implements Bean {

    private static final long serialVersionUID = -1321202352570557192L;

    public static NoteItem toStackBean(WebInputNoteItem webInputNoteItem) {
        if (Objects.isNull(webInputNoteItem)) {
            return null;
        } else {
            return new NoteItem(
                    WebInputLongIdKey.toStackBean(webInputNoteItem.getKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteItem.getNodeKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteItem.getBookKey()),
                    webInputNoteItem.getName(), webInputNoteItem.getRemark(), webInputNoteItem.getLength(),
                    webInputNoteItem.getCreatedDate(), webInputNoteItem.getModifiedDate(),
                    webInputNoteItem.getInspectedDate(), webInputNoteItem.getIndex()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "node_key")
    @Valid
    private WebInputLongIdKey nodeKey;

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

    @JSONField(name = "length")
    @PositiveOrZero
    private long length;

    @JSONField(name = "created_date")
    private Date createdDate;

    @JSONField(name = "modified_date")
    private Date modifiedDate;

    @JSONField(name = "inspected_date")
    private Date inspectedDate;

    @JSONField(name = "index")
    @PositiveOrZero
    private int index;

    public WebInputNoteItem() {
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

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "WebInputNoteItem{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", bookKey=" + bookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", index=" + index +
                '}';
    }
}
