package com.dwarfeng.familyhelper.note.stack.bean.entity;

import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 收藏。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class Favorite implements Entity<FavoriteKey> {

    private static final long serialVersionUID = 111202368191347169L;
    
    private FavoriteKey key;
    private String remark;

    public Favorite() {
    }

    public Favorite(FavoriteKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    @Override
    public FavoriteKey getKey() {
        return key;
    }

    @Override
    public void setKey(FavoriteKey key) {
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
        return "Favorite{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
