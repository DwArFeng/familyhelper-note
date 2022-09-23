package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_attachment_file_info")
public class HibernateAttachmentFileInfo implements Bean {

    private static final long serialVersionUID = 1895745296320546907L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "note_item_id")
    private Long noteItemLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "origin_name")
    private String originName;

    @Column(name = "column_length")
    private long length;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "inspected_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNoteItem.class)
    @JoinColumns({ //
            @JoinColumn(name = "note_item_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteItem noteItem;

    public HibernateAttachmentFileInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getNoteItemKey() {
        return Optional.ofNullable(noteItemLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setNoteItemKey(HibernateLongIdKey idKey) {
        this.noteItemLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getNoteItemLongId() {
        return noteItemLongId;
    }

    public void setNoteItemLongId(Long noteItemLongId) {
        this.noteItemLongId = noteItemLongId;
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

    public HibernateNoteItem getNoteItem() {
        return noteItem;
    }

    public void setNoteItem(HibernateNoteItem noteItem) {
        this.noteItem = noteItem;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "noteItemLongId = " + noteItemLongId + ", " +
                "originName = " + originName + ", " +
                "length = " + length + ", " +
                "createdDate = " + createdDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "inspectedDate = " + inspectedDate + ", " +
                "remark = " + remark + ", " +
                "noteItem = " + noteItem + ")";
    }
}
