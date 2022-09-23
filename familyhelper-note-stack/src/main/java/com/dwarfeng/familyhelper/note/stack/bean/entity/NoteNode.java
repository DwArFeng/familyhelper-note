package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 笔记节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteNode implements Entity<LongIdKey> {

    private static final long serialVersionUID = 568151846475960827L;

    private LongIdKey key;
    private LongIdKey parentKey;
    private LongIdKey bookKey;
    private String name;
    private String remark;

    public NoteNode() {
    }

    public NoteNode(LongIdKey key, LongIdKey parentKey, LongIdKey bookKey, String name, String remark) {
        this.key = key;
        this.parentKey = parentKey;
        this.bookKey = bookKey;
        this.name = name;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
        this.parentKey = parentKey;
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

    @Override
    public String toString() {
        return "NoteNode{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", bookKey=" + bookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
