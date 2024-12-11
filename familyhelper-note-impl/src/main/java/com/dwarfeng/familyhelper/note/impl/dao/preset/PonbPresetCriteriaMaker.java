package com.dwarfeng.familyhelper.note.impl.dao.preset;

import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PonbPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case PonbMaintainService.CHILD_FOR_NOTE_BOOK:
                childForNoteBook(detachedCriteria, objects);
                break;
            case PonbMaintainService.CHILD_FOR_USER:
                childForUser(detachedCriteria, objects);
                break;
            case PonbMaintainService.CHILD_FOR_NOTE_BOOK_PERMISSION_LEVEL_EQUALS:
                childForNoteBookPermissionLevelEquals(detachedCriteria, objects);
                break;
            case PonbMaintainService.CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS:
                childForUserPermissionLevelEquals(detachedCriteria, objects);
                break;
            case PonbMaintainService.CHILD_FOR_NOTE_BOOK_SET:
                childForNoteBookSet(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNoteBook(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteBookLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteBookPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("noteBookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("noteBookLongId", longIdKey.getLongId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForUserPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNoteBookSet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            Collection<LongIdKey> noteBookKeys;
            if (Objects.isNull(objects[0])) {
                noteBookKeys = Collections.emptySet();
            } else {
                @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked"})
                Collection<LongIdKey> dejaVu = (Collection<LongIdKey>) objects[0];
                noteBookKeys = dejaVu;
            }
            Collection<Long> noteBookLongIds = noteBookKeys.stream().map(LongIdKey::getLongId)
                    .collect(Collectors.toSet());
            detachedCriteria.add(Restrictions.in("noteBookLongId", noteBookLongIds));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
