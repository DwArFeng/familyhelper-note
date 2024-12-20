package com.dwarfeng.familyhelper.note.impl.util;

/**
 * FTP 常量。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class FtpConstants {

    public static final String[] PATH_NOTE_FILE = new String[]{"familyhelper-note", "note-file"};
    public static final String[] PATH_ATTACHMENT_FILE = new String[]{"familyhelper-note", "attachment-file"};

    private FtpConstants() {
        throw new IllegalStateException("禁止实例化");
    }
}
