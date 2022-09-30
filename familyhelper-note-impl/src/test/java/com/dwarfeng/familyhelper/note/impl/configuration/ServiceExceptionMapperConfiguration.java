package com.dwarfeng.familyhelper.note.impl.configuration;

import com.dwarfeng.familyhelper.note.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.familyhelper.note.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
        destination.put(UserNotPermittedForNoteBookException.class, ServiceExceptionCodes.USER_NOT_PERMITTED_FOR_NOTE_BOOK);
        destination.put(NoteBookNotExistsException.class, ServiceExceptionCodes.NOTE_BOOK_NOT_EXISTS);
        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
        destination.put(NoteNodeNotExistsException.class, ServiceExceptionCodes.NOTE_NODE_NOT_EXISTS);
        destination.put(IllegalNoteNodeStateException.class, ServiceExceptionCodes.ILLEGAL_NOTE_NODE_STATE);
        destination.put(NoteBookNotIdenticalException.class, ServiceExceptionCodes.NOTE_BOOK_NOT_IDENTICAL);
        destination.put(NoteItemNotExistsException.class, ServiceExceptionCodes.NOTE_ITEM_NOT_EXISTS);
        destination.put(IllegalNoteItemStateException.class, ServiceExceptionCodes.ILLEGAL_NOTE_ITEM_STATE);
        destination.put(AttachmentFileInfoNotExistsException.class, ServiceExceptionCodes.ATTACHMENT_FILE_INFO_NOT_EXISTS);
        destination.put(IllegalAttachmentFileInfoStateException.class, ServiceExceptionCodes.ILLEGAL_ATTACHMENT_FILE_INFO_STATE);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
