package com.dwarfeng.familyhelper.note.sdk.bean.entity;

import com.dwarfeng.familyhelper.note.sdk.bean.key.FastJsonPonbKey;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPonbKey ponbKeyToFastJson(PonbKey ponbKey);

    @InheritInverseConfiguration
    PonbKey ponbKeyFromFastJson(FastJsonPonbKey fastJsonPonbKey);

    FastJsonPonb ponbToFastJson(Ponb ponb);

    @InheritInverseConfiguration
    Ponb ponbFromFastJson(FastJsonPonb fastJsonPonb);

    FastJsonNoteBook noteBookToFastJson(NoteBook noteBook);

    @InheritInverseConfiguration
    NoteBook noteBookFromFastJson(FastJsonNoteBook fastJsonNoteBook);

    FastJsonNoteNode noteNodeToFastJson(NoteNode noteNode);

    @InheritInverseConfiguration
    NoteNode noteNodeFromFastJson(FastJsonNoteNode fastJsonNoteNode);

    FastJsonNoteItem noteItemToFastJson(NoteItem noteItem);

    @InheritInverseConfiguration
    NoteItem noteItemFromFastJson(FastJsonNoteItem fastJsonNoteItem);

    FastJsonAttachmentFileInfo attachmentFileInfoToFastJson(AttachmentFileInfo attachmentFileInfo);

    @InheritInverseConfiguration
    AttachmentFileInfo attachmentFileInfoFromFastJson(FastJsonAttachmentFileInfo fastJsonAttachmentFileInfo);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);
}
