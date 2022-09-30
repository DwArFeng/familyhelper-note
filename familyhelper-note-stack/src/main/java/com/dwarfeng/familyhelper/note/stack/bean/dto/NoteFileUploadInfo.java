package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 笔记文件上传信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NoteFileUploadInfo implements Dto {

    private static final long serialVersionUID = -3544536672562494609L;

    private LongIdKey noteItemKey;
    private byte[] content;

    public NoteFileUploadInfo() {
    }

    public NoteFileUploadInfo(LongIdKey noteItemKey, byte[] content) {
        this.noteItemKey = noteItemKey;
        this.content = content;
    }

    public LongIdKey getNoteItemKey() {
        return noteItemKey;
    }

    public void setNoteItemKey(LongIdKey noteItemKey) {
        this.noteItemKey = noteItemKey;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteFileUploadInfo{" +
                "noteItemKey=" + noteItemKey +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
