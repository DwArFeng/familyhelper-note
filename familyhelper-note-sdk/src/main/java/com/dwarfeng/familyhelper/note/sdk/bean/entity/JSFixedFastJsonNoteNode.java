package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 笔记节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonNoteNode implements Bean {

    private static final long serialVersionUID = -8881042882137974425L;

    public static JSFixedFastJsonNoteNode of(NoteNode noteNode) {
        if (Objects.isNull(noteNode)) {
            return null;
        } else {
            return new JSFixedFastJsonNoteNode(
                    JSFixedFastJsonLongIdKey.of(noteNode.getKey()),
                    JSFixedFastJsonLongIdKey.of(noteNode.getParentKey()),
                    JSFixedFastJsonLongIdKey.of(noteNode.getBookKey()),
                    noteNode.getName(), noteNode.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "parent_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey parentKey;

    @JSONField(name = "book_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey bookKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonNoteNode() {
    }

    public JSFixedFastJsonNoteNode(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey parentKey, JSFixedFastJsonLongIdKey bookKey,
            String name, String remark
    ) {
        this.key = key;
        this.parentKey = parentKey;
        this.bookKey = bookKey;
        this.name = name;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(JSFixedFastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public JSFixedFastJsonLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(JSFixedFastJsonLongIdKey bookKey) {
        this.bookKey = bookKey;
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
        return "JSFixedFastJsonNoteNode{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", bookKey=" + bookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
