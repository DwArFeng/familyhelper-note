package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 笔记本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonNoteBook implements Bean {

    private static final long serialVersionUID = 1939944580811255857L;

    public static FastJsonNoteBook of(NoteBook noteBook) {
        if (Objects.isNull(noteBook)) {
            return null;
        } else {
            return new FastJsonNoteBook(
                    FastJsonLongIdKey.of(noteBook.getKey()),
                    noteBook.getName(), noteBook.getRemark(), noteBook.getCreatedDate(),
                    noteBook.getItemCount(), noteBook.getLastModifiedDate(), noteBook.getLastInspectedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "created_date", ordinal = 4)
    private Date createdDate;

    @JSONField(name = "item_count", ordinal = 5)
    private int itemCount;

    @JSONField(name = "last_modified_date", ordinal = 6)
    private Date lastModifiedDate;

    @JSONField(name = "last_inspected_date", ordinal = 7)
    private Date lastInspectedDate;

    public FastJsonNoteBook() {
    }

    public FastJsonNoteBook(
            FastJsonLongIdKey key, String name, String remark, Date createdDate, int itemCount, Date lastModifiedDate,
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

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonNoteBook{" +
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
