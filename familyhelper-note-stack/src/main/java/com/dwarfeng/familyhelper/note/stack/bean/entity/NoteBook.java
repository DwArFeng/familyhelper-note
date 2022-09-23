package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 笔记本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBook implements Entity<LongIdKey> {

    private static final long serialVersionUID = -4589594756582602519L;

    private LongIdKey key;
    private String name;
    private String remark;
    private Date createdDate;
    private int itemCount;
    private Date lastModifiedDate;
    private Date lastInspectedDate;

    public NoteBook() {
    }

    public NoteBook(
            LongIdKey key, String name, String remark, Date createdDate, int itemCount, Date lastModifiedDate,
            Date lastInspectedDate
    ) {
        this.key = key;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
        this.itemCount = itemCount;
        this.lastModifiedDate = lastModifiedDate;
        this.lastInspectedDate = lastInspectedDate;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastInspectedDate() {
        return lastInspectedDate;
    }

    public void setLastInspectedDate(Date lastInspectedDate) {
        this.lastInspectedDate = lastInspectedDate;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                ", itemCount=" + itemCount +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastInspectedDate=" + lastInspectedDate +
                '}';
    }
}
