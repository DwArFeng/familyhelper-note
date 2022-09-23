package com.dwarfeng.familyhelper.note.stack.dao;

import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 附件文件信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AttachmentFileInfoDao extends BatchBaseDao<LongIdKey, AttachmentFileInfo>, EntireLookupDao<AttachmentFileInfo>,
        PresetLookupDao<AttachmentFileInfo> {
}
