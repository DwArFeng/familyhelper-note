package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.note.sdk.bean.key.FastJsonFavoriteKey;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 收藏。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonFavorite implements Bean {

    private static final long serialVersionUID = -7478153410968219704L;

    public static FastJsonFavorite of(Favorite favorite) {
        if (Objects.isNull(favorite)) {
            return null;
        } else {
            return new FastJsonFavorite(
                    FastJsonFavoriteKey.of(favorite.getKey()),
                    favorite.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonFavoriteKey key;

    @JSONField(name = "remark", ordinal = 2)
    private String remark;

    public FastJsonFavorite() {
    }

    public FastJsonFavorite(FastJsonFavoriteKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public FastJsonFavoriteKey getKey() {
        return key;
    }

    public void setKey(FastJsonFavoriteKey key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonFavorite{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
