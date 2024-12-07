package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 笔记本创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookCreateInfo implements Dto {

    private static final long serialVersionUID = 8615016412466052902L;
    
    private String name;
    private String remark;

    /**
     * @since 1.1.0
     */
    private boolean favorite;

    public NoteBookCreateInfo() {
    }

    public NoteBookCreateInfo(String name, String remark, boolean favorite) {
        this.name = name;
        this.remark = remark;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "NoteBookCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
