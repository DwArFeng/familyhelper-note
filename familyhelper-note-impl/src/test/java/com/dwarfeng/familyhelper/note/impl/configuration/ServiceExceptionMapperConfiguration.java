package com.dwarfeng.familyhelper.note.impl.configuration;

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
//        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
//        destination.put(UserNotPermittedForPbSetException.class, ServiceExceptionCodes.USER_NOT_PERMITTED_FOR_PB_SET);
//        destination.put(PbSetNotExistsException.class, ServiceExceptionCodes.PB_SET_NOT_EXISTS);
//        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
//        destination.put(PbNodeNotExistsException.class, ServiceExceptionCodes.PB_NODE_NOT_EXISTS);
//        destination.put(IllegalPbNodeStateException.class, ServiceExceptionCodes.ILLEGAL_PB_NODE_STATE);
//        destination.put(PbSetNotIdenticalException.class, ServiceExceptionCodes.PB_SET_NOT_IDENTICAL);
//        destination.put(PbItemNotExistsException.class, ServiceExceptionCodes.PB_ITEM_NOT_EXISTS);
//        destination.put(IllegalPbItemStateException.class, ServiceExceptionCodes.ILLEGAL_PB_ITEM_STATE);
//        destination.put(PbRecordNotExistsException.class, ServiceExceptionCodes.PB_RECORD_NOT_EXISTS);
//        destination.put(IllegalPbRecordStateException.class, ServiceExceptionCodes.ILLEGAL_PB_RECORD_STATE);
//        destination.put(PbFileNotExistsException.class, ServiceExceptionCodes.PB_FILE_NOT_EXISTS);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
