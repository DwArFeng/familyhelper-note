package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.bean.key.FastJsonPonbKey;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 笔记本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPonb implements Bean {

    private static final long serialVersionUID = -2867730640519045662L;

    public static FastJsonPonb of(Ponb ponb) {
        if (Objects.isNull(ponb)) {
            return null;
        } else {
            return new FastJsonPonb(FastJsonPonbKey.of(ponb.getKey()), ponb.getPermissionLevel(), ponb.getRemark());
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPonbKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonPonb() {
    }

    public FastJsonPonb(FastJsonPonbKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public FastJsonPonbKey getKey() {
        return key;
    }

    public void setKey(FastJsonPonbKey key) {
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
        return "FastJsonPonb{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
