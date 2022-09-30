package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 附件文件上传信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AttachmentFileUploadInfo implements Dto {

    private static final long serialVersionUID = 6701341471169181390L;

    private LongIdKey noteItemKey;
    private String originName;
    private byte[] content;

    public AttachmentFileUploadInfo() {
    }

    public AttachmentFileUploadInfo(LongIdKey noteItemKey, String originName, byte[] content) {
        this.noteItemKey = noteItemKey;
        this.originName = originName;
        this.content = content;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AttachmentFileUploadInfo{" +
                "noteItemKey=" + noteItemKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
