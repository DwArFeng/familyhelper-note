package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 笔记节点创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteNodeCreateInfo implements Dto {

    private static final long serialVersionUID = -4664906439000134528L;

    private LongIdKey bookKey;
    private LongIdKey parentKey;
    private String name;
    private String remark;

    public NoteNodeCreateInfo() {
    }

    public NoteNodeCreateInfo(
            LongIdKey bookKey, LongIdKey parentKey, String name, String remark
    ) {
        this.bookKey = bookKey;
        this.parentKey = parentKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(LongIdKey bookKey) {
        this.bookKey = bookKey;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
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
        return "NoteNodeCreateInfo{" +
                "bookKey=" + bookKey +
                ", parentKey=" + parentKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
