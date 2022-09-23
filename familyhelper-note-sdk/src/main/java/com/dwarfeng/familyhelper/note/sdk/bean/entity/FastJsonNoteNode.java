package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 笔记节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonNoteNode implements Bean {

    private static final long serialVersionUID = -5573818482891334577L;

    public static FastJsonNoteNode of(NoteNode noteNode) {
        if (Objects.isNull(noteNode)) {
            return null;
        } else {
            return new FastJsonNoteNode(
                    FastJsonLongIdKey.of(noteNode.getKey()),
                    FastJsonLongIdKey.of(noteNode.getParentKey()),
                    FastJsonLongIdKey.of(noteNode.getBookKey()),
                    noteNode.getName(), noteNode.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "parent_key", ordinal = 2)
    private FastJsonLongIdKey parentKey;

    @JSONField(name = "book_key", ordinal = 3)
    private FastJsonLongIdKey bookKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonNoteNode() {
    }

    public FastJsonNoteNode(
            FastJsonLongIdKey key, FastJsonLongIdKey parentKey, FastJsonLongIdKey bookKey, String name, String remark
    ) {
        this.key = key;
        this.parentKey = parentKey;
        this.bookKey = bookKey;
        this.name = name;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(FastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public FastJsonLongIdKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(FastJsonLongIdKey bookKey) {
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
        return "FastJsonNoteNode{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", bookKey=" + bookKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
