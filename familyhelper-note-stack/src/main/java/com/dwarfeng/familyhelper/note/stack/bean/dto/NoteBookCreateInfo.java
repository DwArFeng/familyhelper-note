package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 笔记本创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookCreateInfo implements Dto {

    private static final long serialVersionUID = 7167832006030445952L;

    private String name;
    private String remark;

    public NoteBookCreateInfo() {
    }

    public NoteBookCreateInfo(String name, String remark) {
        this.name = name;
        this.remark = remark;
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
        return "NoteBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
