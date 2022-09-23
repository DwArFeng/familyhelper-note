package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 附件文件信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonAttachmentFileInfo implements Bean {

    private static final long serialVersionUID = 6536815787270973971L;

    public static JSFixedFastJsonAttachmentFileInfo of(AttachmentFileInfo attachmentFileInfo) {
        if (Objects.isNull(attachmentFileInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonAttachmentFileInfo(
                    JSFixedFastJsonLongIdKey.of(attachmentFileInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(attachmentFileInfo.getNoteItemKey()),
                    attachmentFileInfo.getOriginName(), attachmentFileInfo.getLength(),
                    attachmentFileInfo.getCreatedDate(), attachmentFileInfo.getModifiedDate(),
                    attachmentFileInfo.getInspectedDate(), attachmentFileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "note_item_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey noteItemKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "length", ordinal = 4)
    private long length;

    @JSONField(name = "created_date", ordinal = 5)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "inspected_date", ordinal = 7)
    private Date inspectedDate;

    @JSONField(name = "remark", ordinal = 8)
    private String remark;

    public JSFixedFastJsonAttachmentFileInfo() {
    }

    public JSFixedFastJsonAttachmentFileInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey noteItemKey, String originName, long length,
            Date createdDate, Date modifiedDate, Date inspectedDate, String remark
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

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNoteItemKey() {
        return noteItemKey;
    }

    public void setNoteItemKey(JSFixedFastJsonLongIdKey noteItemKey) {
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
        return "JSFixedFastJsonAttachmentFileInfo{" +
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
