package com.dwarfeng.familyhelper.note.sdk.bean.key.formatter;

import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * FavoriteKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FavoriteStringKeyFormatter implements StringKeyFormatter<FavoriteKey> {

    private String prefix;

    public FavoriteStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(FavoriteKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNoteBookLongId() + "_" + key.getUserStringId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "FavoriteStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
