package com.dwarfeng.familyhelper.note.impl.dao.preset;

import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NoteItemPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case NoteItemMaintainService.CHILD_FOR_NODE:
                childForNode(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_BOOK:
                childForBook(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_BOOK_ROOT:
                childForBookRoot(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.NAME_LIKE:
                nameLike(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_BOOK_NAME_LIKE:
                childForBookNameLike(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_NODE_INDEX_ASC:
                childForNodeIndexAsc(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_NODE_INDEX_DESC:
                childForNodeIndexDesc(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_BOOK_ROOT_INDEX_ASC:
                childForBookRootIndexAsc(detachedCriteria, objects);
                break;
            case NoteItemMaintainService.CHILD_FOR_BOOK_ROOT_INDEX_DESC:
                childForBookRootIndexDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForNode(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForBook(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bookLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBookRoot(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bookLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("nodeLongId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void nameLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String pattern = (String) objects[0];
            detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForBookNameLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bookLongId", longIdKey.getLongId())
                );
            }
            String pattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeIndexAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeIndexDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBookRootIndexAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bookLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("nodeLongId"));
            detachedCriteria.addOrder(Order.asc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForBookRootIndexDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("bookLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("bookLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("nodeLongId"));
            detachedCriteria.addOrder(Order.desc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
