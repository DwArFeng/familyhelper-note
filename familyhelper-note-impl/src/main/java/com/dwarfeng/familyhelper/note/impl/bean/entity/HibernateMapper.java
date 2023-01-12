package com.dwarfeng.familyhelper.note.impl.bean.entity;

import com.dwarfeng.familyhelper.note.impl.bean.key.HibernatePonbKey;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernatePonbKey ponbKeyToHibernate(PonbKey ponbKey);

    @InheritInverseConfiguration
    PonbKey ponbKeyFromHibernate(HibernatePonbKey hibernatePonbKey);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "noteBookLongId", ignore = true)
    @Mapping(target = "noteBook", ignore = true)
    HibernatePonb ponbToHibernate(Ponb ponb);

    @InheritInverseConfiguration
    Ponb ponbFromHibernate(HibernatePonb hibernatePonb);

    @Mapping(target = "ponbs", ignore = true)
    @Mapping(target = "nodes", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "items", ignore = true)
    HibernateNoteBook noteBookToHibernate(NoteBook noteBook);

    @InheritInverseConfiguration
    NoteBook noteBookFromHibernate(HibernateNoteBook hibernateNoteBook);

    @Mapping(target = "parentLongId", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "noteItems", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "bookLongId", ignore = true)
    @Mapping(target = "book", ignore = true)
    HibernateNoteNode noteNodeToHibernate(NoteNode noteNode);

    @InheritInverseConfiguration
    NoteNode noteNodeFromHibernate(HibernateNoteNode hibernateNoteNode);

    @Mapping(target = "nodeLongId", ignore = true)
    @Mapping(target = "node", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "bookLongId", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "attachmentFileInfos", ignore = true)
    HibernateNoteItem noteItemToHibernate(NoteItem noteItem);

    @InheritInverseConfiguration
    NoteItem noteItemFromHibernate(HibernateNoteItem hibernateNoteItem);

    @Mapping(target = "noteItemLongId", ignore = true)
    @Mapping(target = "noteItem", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateAttachmentFileInfo attachmentFileInfoToHibernate(AttachmentFileInfo attachmentFileInfo);

    @InheritInverseConfiguration
    AttachmentFileInfo attachmentFileInfoFromHibernate(HibernateAttachmentFileInfo hibernateAttachmentFileInfo);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "ponbs", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);
}
