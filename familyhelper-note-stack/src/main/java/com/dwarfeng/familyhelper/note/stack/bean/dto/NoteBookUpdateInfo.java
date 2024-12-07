package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 笔记本更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteBookUpdateInfo implements Dto {

    private static final long serialVersionUID = -8260390569301679543L;
    
    private LongIdKey noteBookKey;
    private String name;
    private String remark;

    /**
     * @since 1.1.0
     */
    private boolean favorite;

    public NoteBookUpdateInfo() {
    }

    public NoteBookUpdateInfo(LongIdKey noteBookKey, String name, String remark, boolean favorite) {
        this.noteBookKey = noteBookKey;
        this.name = name;
        this.remark = remark;
        this.favorite = favorite;
    }

    public LongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(LongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
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
        return "NoteBookUpdateInfo{" +
                "noteBookKey=" + noteBookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
