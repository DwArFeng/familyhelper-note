package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 笔记本收藏变更信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NoteBookFavoredChangeInfo implements Dto {

    private static final long serialVersionUID = 8996560637227001391L;
    
    private LongIdKey noteBookKey;

    public NoteBookFavoredChangeInfo() {
    }

    public NoteBookFavoredChangeInfo(LongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    public LongIdKey getNoteBookKey() {
        return noteBookKey;
    }

    public void setNoteBookKey(LongIdKey noteBookKey) {
        this.noteBookKey = noteBookKey;
    }

    @Override
    public String toString() {
        return "NoteBookFavoredChangeInfo{" +
                "noteBookKey=" + noteBookKey +
                '}';
    }
}
