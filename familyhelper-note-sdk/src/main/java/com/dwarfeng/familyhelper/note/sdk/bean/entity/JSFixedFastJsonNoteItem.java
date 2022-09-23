package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 笔记项目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonNoteItem implements Bean {

    private static final long serialVersionUID = -5420115714838141490L;

    public static JSFixedFastJsonNoteItem of(NoteItem noteItem) {
        if (Objects.isNull(noteItem)) {
            return null;
        } else {
            return new JSFixedFastJsonNoteItem(
                    JSFixedFastJsonLongIdKey.of(noteItem.getKey()),
                    JSFixedFastJsonLongIdKey.of(noteItem.getNodeKey()),
                    JSFixedFastJsonLongIdKey.of(noteItem.getBookKey()),
                    noteItem.getName(), noteItem.getRemark(), noteItem.getLength(), noteItem.getCreatedDate(),
                    noteItem.getModifiedDate(), noteItem.getInspectedDate(), noteItem.getIndex()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "node_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey nodeKey;

    @JSONField(name = "book_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey bookKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    @JSONField(name = "length", ordinal = 6)
    private long length;

    @JSONField(name = "created_date", ordinal = 7)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 8)
    private Date modifiedDate;

    @JSONField(name = "inspected_date", ordinal = 9)
    private Date inspectedDate;

    @JSONField(name = "index", ordinal = 10)
    private int index;

    public JSFixedFastJsonNoteItem() {
    }

    public JSFixedFastJsonNoteItem(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey nodeKey, JSFixedFastJsonLongIdKey bookKey,
            String name, String remark, long length, Date createdDate, Date modifiedDate, Date inspectedDate, int index
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.bookKey = bookKey;
        this.name = name;
        this.remark = remark;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
        this.index = index;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(JSFixedFastJsonLongIdKey nodeKey) {
        this.nodeKey = nodeKey;
    }

    public JSFixedFastJsonLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(JSFixedFastJsonLongIdKey bookKey) {
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
        return "JSFixedFastJsonNoteItem{" +
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
