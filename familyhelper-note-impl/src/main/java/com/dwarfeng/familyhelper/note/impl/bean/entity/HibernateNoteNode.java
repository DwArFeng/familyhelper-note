package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_note_node")
public class HibernateNoteNode implements Bean {

    private static final long serialVersionUID = -7505731145549528322L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "parent_id")
    private Long parentLongId;

    @Column(name = "book_id")
    private Long bookLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNoteNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteNode parent;

    @ManyToOne(targetEntity = HibernateNoteBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteBook book;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNoteItem.class, mappedBy = "node")
    private Set<HibernateNoteItem> noteItems = new HashSet<>();

    public HibernateNoteNode() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getParentKey() {
        return Optional.ofNullable(parentLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setParentKey(HibernateLongIdKey idKey) {
        this.parentLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
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

    public Long getParentLongId() {
        return parentLongId;
    }

    public void setParentLongId(Long parentLongId) {
        this.parentLongId = parentLongId;
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

    public HibernateNoteNode getParent() {
        return parent;
    }

    public void setParent(HibernateNoteNode parent) {
        this.parent = parent;
    }

    public HibernateNoteBook getBook() {
        return book;
    }

    public void setBook(HibernateNoteBook book) {
        this.book = book;
    }

    public Set<HibernateNoteItem> getNoteItems() {
        return noteItems;
    }

    public void setNoteItems(Set<HibernateNoteItem> noteItems) {
        this.noteItems = noteItems;
    }

    @Override
    public String toString() {
        return "HibernateNoteNode{" +
                "longId=" + longId +
                ", parentLongId=" + parentLongId +
                ", bookLongId=" + bookLongId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", parent=" + parent +
                ", book=" + book +
                ", noteItems=" + noteItems +
                '}';
    }
}
