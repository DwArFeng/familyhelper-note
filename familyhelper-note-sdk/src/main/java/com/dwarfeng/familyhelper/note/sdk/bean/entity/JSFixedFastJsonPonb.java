package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.bean.key.JSFixedFastJsonPonbKey;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 笔记本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPonb implements Bean {

    private static final long serialVersionUID = -6631857357249361309L;

    public static JSFixedFastJsonPonb of(Ponb ponb) {
        if (Objects.isNull(ponb)) {
            return null;
        } else {
            return new JSFixedFastJsonPonb(
                    JSFixedFastJsonPonbKey.of(ponb.getKey()), ponb.getPermissionLevel(), ponb.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonPonbKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonPonb() {
    }

    public JSFixedFastJsonPonb(JSFixedFastJsonPonbKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public JSFixedFastJsonPonbKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonPonbKey key) {
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
        return "JSFixedFastJsonPonb{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
