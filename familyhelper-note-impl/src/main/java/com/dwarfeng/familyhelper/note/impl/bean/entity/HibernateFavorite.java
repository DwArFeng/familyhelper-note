package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.impl.bean.key.HibernateFavoriteKey;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateFavoriteKey.class)
@Table(name = "tbl_favorite")
public class HibernateFavorite implements Bean {

    private static final long serialVersionUID = 7053685371648563869L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "note_book_id", nullable = false)
    private Long noteBookLongId;

    @Id
    @Column(name = "user_id", length = Constraints.LENGTH_USER, nullable = false)
    private String userStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
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

    public HibernateFavorite() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateFavoriteKey getKey() {
        return new HibernateFavoriteKey(noteBookLongId, userStringId);
    }

    public void setKey(HibernateFavoriteKey key) {
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
                "remark = " + remark + ", " +
                "noteBook = " + noteBook + ", " +
                "user = " + user + ")";
    }
}
