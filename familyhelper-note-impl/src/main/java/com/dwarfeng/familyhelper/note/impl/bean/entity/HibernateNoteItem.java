package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_note_item")
public class HibernateNoteItem implements Bean {

    private static final long serialVersionUID = 7292254753318435834L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "node_id")
    private Long nodeLongId;

    @Column(name = "book_id")
    private Long bookLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

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

    @Column(name = "column_index")
    private int index;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNoteNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "node_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteNode node;

    @ManyToOne(targetEntity = HibernateNoteBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteBook book;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAttachmentFileInfo.class, mappedBy = "noteItemLongId")
    private Set<HibernateAttachmentFileInfo> attachmentFileInfos = new HashSet<>();

    public HibernateNoteItem() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getNodeKey() {
        return Optional.ofNullable(nodeLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setNodeKey(HibernateLongIdKey idKey) {
        this.nodeLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getBookKey() {
        return Optional.ofNullable(bookLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setBookKey(HibernateLongIdKey idKey) {
        this.bookLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getNodeLongId() {
        return nodeLongId;
    }

    public void setNodeLongId(Long nodeLongId) {
        this.nodeLongId = nodeLongId;
    }

    public Long getBookLongId() {
        return bookLongId;
    }

    public void setBookLongId(Long bookLongId) {
        this.bookLongId = bookLongId;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public HibernateNoteNode getNode() {
        return node;
    }

    public void setNode(HibernateNoteNode node) {
        this.node = node;
    }

    public HibernateNoteBook getBook() {
        return book;
    }

    public void setBook(HibernateNoteBook book) {
        this.book = book;
    }

    public Set<HibernateAttachmentFileInfo> getAttachmentFileInfos() {
        return attachmentFileInfos;
    }

    public void setAttachmentFileInfos(Set<HibernateAttachmentFileInfo> attachmentFileInfos) {
        this.attachmentFileInfos = attachmentFileInfos;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "nodeLongId = " + nodeLongId + ", " +
                "bookLongId = " + bookLongId + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "length = " + length + ", " +
                "createdDate = " + createdDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "inspectedDate = " + inspectedDate + ", " +
                "index = " + index + ", " +
                "node = " + node + ", " +
                "book = " + book + ")";
    }
}
