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
@Table(name = "tbl_note_book")
public class HibernateNoteBook implements Bean {

    private static final long serialVersionUID = 4413690131391904574L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "last_inspected_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInspectedDate;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNoteNode.class, mappedBy = "book")
    private Set<HibernateNoteNode> nodes = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNoteItem.class, mappedBy = "book")
    private Set<HibernateNoteItem> items = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePonb.class, mappedBy = "noteBook")
    private Set<HibernatePonb> ponbs = new HashSet<>();

    public HibernateNoteBook() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastInspectedDate() {
        return lastInspectedDate;
    }

    public void setLastInspectedDate(Date lastInspectedDate) {
        this.lastInspectedDate = lastInspectedDate;
    }

    public Set<HibernateNoteNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<HibernateNoteNode> nodes) {
        this.nodes = nodes;
    }

    public Set<HibernateNoteItem> getItems() {
        return items;
    }

    public void setItems(Set<HibernateNoteItem> items) {
        this.items = items;
    }

    public Set<HibernatePonb> getPonbs() {
        return ponbs;
    }

    public void setPonbs(Set<HibernatePonb> ponbs) {
        this.ponbs = ponbs;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "createdDate = " + createdDate + ", " +
                "itemCount = " + itemCount + ", " +
                "lastModifiedDate = " + lastModifiedDate + ", " +
                "lastInspectedDate = " + lastInspectedDate + ")";
    }
}
