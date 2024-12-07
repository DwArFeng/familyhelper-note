package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.impl.bean.key.HibernatePonbKey;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePonbKey.class)
@Table(name = "tbl_ponb")
public class HibernatePonb implements Bean {

    private static final long serialVersionUID = 8397250339636645933L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "note_book_id", nullable = false)
    private Long noteBookLongId;

    @Id
    @Column(name = "user_id", length = Constraints.LENGTH_USER, nullable = false)
    private String userStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "permission_level")
    private int permissionLevel;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNoteBook.class)
    @JoinColumns({ //
            @JoinColumn(name = "note_book_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNoteBook noteBook;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernatePonb() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernatePonbKey getKey() {
        return new HibernatePonbKey(noteBookLongId, userStringId);
    }

    public void setKey(HibernatePonbKey key) {
        if (Objects.isNull(key)) {
            this.noteBookLongId = null;
            this.userStringId = null;
        } else {
            this.noteBookLongId = key.getNoteBookLongId();
            this.userStringId = key.getUserStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getNoteBookLongId() {
        return noteBookLongId;
    }

    public void setNoteBookLongId(Long noteBookLongId) {
        this.noteBookLongId = noteBookLongId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateNoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(HibernateNoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "noteBookLongId = " + noteBookLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "permissionLevel = " + permissionLevel + ", " +
                "remark = " + remark + ", " +
                "noteBook = " + noteBook + ", " +
                "user = " + user + ")";
    }
}
