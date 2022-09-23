package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 笔记本。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteBook implements Bean {

    private static final long serialVersionUID = -7669851322327008167L;

    public static NoteBook toStackBean(WebInputNoteBook webInputNoteBook) {
        if (Objects.isNull(webInputNoteBook)) {
            return null;
        } else {
            return new NoteBook(
                    WebInputLongIdKey.toStackBean(webInputNoteBook.getKey()),
                    webInputNoteBook.getName(), webInputNoteBook.getRemark(), webInputNoteBook.getCreatedDate(),
                    webInputNoteBook.getItemCount(), webInputNoteBook.getLastModifiedDate(), webInputNoteBook.getLastInspectedDate()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "created_date")
    private Date createdDate;

    @JSONField(name = "item_count")
    @PositiveOrZero
    private int itemCount;

    @JSONField(name = "last_modified_date")
    private Date lastModifiedDate;

    @JSONField(name = "last_inspected_date")
    private Date lastInspectedDate;

    public WebInputNoteBook() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "WebInputNoteBook{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                ", itemCount=" + itemCount +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastInspectedDate=" + lastInspectedDate +
                '}';
    }
}
