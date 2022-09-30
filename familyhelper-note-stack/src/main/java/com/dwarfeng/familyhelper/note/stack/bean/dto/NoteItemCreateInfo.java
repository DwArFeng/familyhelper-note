package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 笔记项目创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteItemCreateInfo implements Dto {

    private static final long serialVersionUID = -2807745751173989205L;

    private LongIdKey bookKey;
    private LongIdKey nodeKey;
    private String name;
    private String remark;

    public NoteItemCreateInfo() {
    }

    public NoteItemCreateInfo(
            LongIdKey bookKey, LongIdKey nodeKey, String name, String remark
    ) {
        this.bookKey = bookKey;
        this.nodeKey = nodeKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(LongIdKey bookKey) {
        this.bookKey = bookKey;
    }

    public LongIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(LongIdKey nodeKey) {
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
        return "NoteItemCreateInfo{" +
                "bookKey=" + bookKey +
                ", nodeKey=" + nodeKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
