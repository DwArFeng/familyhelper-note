package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 笔记项目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteItem implements Entity<LongIdKey> {

    private static final long serialVersionUID = 1626739928704709211L;

    private LongIdKey key;
    private LongIdKey nodeKey;
    private LongIdKey bookKey;
    private String name;
    private String remark;
    private long length;
    private Date createdDate;
    private Date modifiedDate;
    private Date inspectedDate;
    private int index;

    public NoteItem() {
    }

    public NoteItem(
            LongIdKey key, LongIdKey nodeKey, LongIdKey bookKey, String name, String remark, long length,
            Date createdDate, Date modifiedDate, Date inspectedDate, int index
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

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(LongIdKey nodeKey) {
        this.nodeKey = nodeKey;
    }

    public LongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(LongIdKey bookKey) {
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
        return "NoteItem{" +
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
