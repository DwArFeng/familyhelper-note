package com.dwarfeng.familyhelper.note.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.util.Constraints;
import com.dwarfeng.familyhelper.note.stack.bean.dto.NoteItemCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 笔记项目创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNoteItemCreateInfo implements Dto {

    private static final long serialVersionUID = -3827807084379414386L;

    public static NoteItemCreateInfo toStackBean(WebInputNoteItemCreateInfo webInputNoteItemCreateInfo) {
        if (Objects.isNull(webInputNoteItemCreateInfo)) {
            return null;
        } else {
            return new NoteItemCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputNoteItemCreateInfo.getBookKey()),
                    WebInputLongIdKey.toStackBean(webInputNoteItemCreateInfo.getNodeKey()),
                    webInputNoteItemCreateInfo.getName(), webInputNoteItemCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "book_key")
    @Valid
    private WebInputLongIdKey bookKey;

    @JSONField(name = "node_key")
    @Valid
    private WebInputLongIdKey nodeKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputNoteItemCreateInfo() {
    }

    public WebInputLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(WebInputLongIdKey bookKey) {
        this.bookKey = bookKey;
    }

    public WebInputLongIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(WebInputLongIdKey nodeKey) {
        this.nodeKey = nodeKey;
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

    @Override
    public String toString() {
        return "WebInputNoteItemCreateInfo{" +
                "bookKey=" + bookKey +
                ", nodeKey=" + nodeKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
