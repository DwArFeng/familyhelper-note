package com.dwarfeng.familyhelper.note.impl.dao.preset;

import com.dwarfeng.familyhelper.note.stack.service.AttachmentFileInfoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class AttachmentFileInfoPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM:
                childForNoteItem(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_ORIGIN_NAME_ASC:
                childForNoteItemOriginNameAsc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_ORIGIN_NAME_DESC:
                childForNoteItemOriginNameDesc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_CREATED_DATE_ASC:
                childForNoteItemCreatedDateAsc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_CREATED_DATE_DESC:
                childForNoteItemCreatedDateDesc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_MODIFIED_DATE_ASC:
                childForNoteItemModifiedDateAsc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_MODIFIED_DATE_DESC:
                childForNoteItemModifiedDateDesc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_INSPECTED_DATE_ASC:
                childForNoteItemInspectedDateAsc(detachedCriteria, objects);
                break;
            case AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM_INSPECTED_DATE_DESC:
                childForNoteItemInspectedDateDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForNoteItem(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemOriginNameAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("originName"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemOriginNameDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("originName"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemCreatedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemCreatedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemModifiedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("modifiedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemModifiedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("modifiedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemInspectedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("inspectedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteItemInspectedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteItemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteItemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("inspectedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
