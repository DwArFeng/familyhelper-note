package com.dwarfeng.familyhelper.note.stack.cache;

import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 附件文件信息缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AttachmentFileInfoCache extends BatchBaseCache<LongIdKey, AttachmentFileInfo> {
}
