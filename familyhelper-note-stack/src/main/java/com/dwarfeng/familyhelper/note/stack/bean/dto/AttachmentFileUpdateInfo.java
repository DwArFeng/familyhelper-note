package com.dwarfeng.familyhelper.note.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 附件文件更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AttachmentFileUpdateInfo implements Dto {

    private static final long serialVersionUID = 4824256880083929483L;

    private LongIdKey attachmentFileInfoKey;
    private String originName;
    private byte[] content;

    public AttachmentFileUpdateInfo() {
    }

    public AttachmentFileUpdateInfo(LongIdKey attachmentFileInfoKey, String originName, byte[] content) {
        this.attachmentFileInfoKey = attachmentFileInfoKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getAttachmentFileInfoKey() {
        return attachmentFileInfoKey;
    }

    public void setAttachmentFileInfoKey(LongIdKey attachmentFileInfoKey) {
        this.attachmentFileInfoKey = attachmentFileInfoKey;
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
        return "AttachmentFileUpdateInfo{" +
                "attachmentFileInfoKey=" + attachmentFileInfoKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
