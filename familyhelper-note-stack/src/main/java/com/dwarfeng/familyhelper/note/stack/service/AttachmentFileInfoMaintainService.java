package com.dwarfeng.familyhelper.note.stack.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 附件文件信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AttachmentFileInfoMaintainService extends BatchCrudService<LongIdKey, AttachmentFileInfo>,
        EntireLookupService<AttachmentFileInfo>, PresetLookupService<AttachmentFileInfo> {

    String CHILD_FOR_NOTE_ITEM = "child_for_note_item";
    String CHILD_FOR_NOTE_ITEM_ORIGIN_NAME_ASC = "child_for_note_item_origin_name_asc";
    String CHILD_FOR_NOTE_ITEM_ORIGIN_NAME_DESC = "child_for_note_item_origin_name_desc";
    String CHILD_FOR_NOTE_ITEM_CREATED_DATE_ASC = "child_for_note_item_created_date_asc";
    String CHILD_FOR_NOTE_ITEM_CREATED_DATE_DESC = "child_for_note_item_created_date_desc";
    String CHILD_FOR_NOTE_ITEM_MODIFIED_DATE_ASC = "child_for_note_item_modified_date_asc";
    String CHILD_FOR_NOTE_ITEM_MODIFIED_DATE_DESC = "child_for_note_item_modified_date_desc";
    String CHILD_FOR_NOTE_ITEM_INSPECTED_DATE_ASC = "child_for_note_item_inspected_date_asc";
    String CHILD_FOR_NOTE_ITEM_INSPECTED_DATE_DESC = "child_for_note_item_inspected_date_desc";
}
