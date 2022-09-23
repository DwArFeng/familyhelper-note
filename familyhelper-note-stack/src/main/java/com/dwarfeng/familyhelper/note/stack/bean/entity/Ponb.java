package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 笔记本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Ponb implements Entity<PonbKey> {

    private static final long serialVersionUID = -7229668329865255659L;

    private PonbKey key;
    private int permissionLevel;
    private String remark;

    public Ponb() {
    }

    public Ponb(PonbKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    @Override
    public PonbKey getKey() {
        return key;
    }

    @Override
    public void setKey(PonbKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Ponb{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
