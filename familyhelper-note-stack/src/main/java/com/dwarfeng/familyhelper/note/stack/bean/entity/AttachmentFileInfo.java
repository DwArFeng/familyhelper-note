package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 附件文件信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AttachmentFileInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -1759080381672442603L;

    private LongIdKey key;
    private LongIdKey noteItemKey;
    private String originName;
    private long length;
    private Date createdDate;
    private Date modifiedDate;
    private Date inspectedDate;
    private String remark;

    public AttachmentFileInfo() {
    }

    public AttachmentFileInfo(
            LongIdKey key, LongIdKey noteItemKey, String originName, long length, Date createdDate, Date modifiedDate,
            Date inspectedDate, String remark
    ) {
        this.key = key;
        this.noteItemKey = noteItemKey;
        this.originName = originName;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
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

    public LongIdKey getNoteItemKey() {
        return noteItemKey;
    }

    public void setNoteItemKey(LongIdKey noteItemKey) {
        this.noteItemKey = noteItemKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AttachmentFileInfo{" +
                "key=" + key +
                ", noteItemKey=" + noteItemKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
