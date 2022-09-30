package com.dwarfeng.familyhelper.note.node.configuration;

import com.dwarfeng.familyhelper.note.impl.service.operation.*;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.cache.PonbCache;
import com.dwarfeng.familyhelper.note.stack.dao.*;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final UserCrudOperation userCrudOperation;
    private final PonbDao ponbDao;
    private final PonbCache ponbCache;
    private final NoteBookCrudOperation noteBookCrudOperation;
    private final NoteBookDao noteBookDao;
    private final NoteNodeCrudOperation noteNodeCrudOperation;
    private final NoteNodeDao noteNodeDao;
    private final NoteItemCrudOperation noteItemCrudOperation;
    private final NoteItemDao noteItemDao;
    private final AttachmentFileInfoCrudOperation attachmentFileInfoCrudOperation;
    private final AttachmentFileInfoDao attachmentFileInfoDao;

    @Value("${cache.timeout.entity.ponb}")
    private long ponbTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            UserCrudOperation userCrudOperation,
            PonbDao ponbDao, PonbCache ponbCache,
            NoteBookCrudOperation noteBookCrudOperation, NoteBookDao noteBookDao,
            NoteNodeCrudOperation noteNodeCrudOperation, NoteNodeDao noteNodeDao,
            NoteItemCrudOperation noteItemCrudOperation, NoteItemDao noteItemDao,
            AttachmentFileInfoCrudOperation attachmentFileInfoCrudOperation, AttachmentFileInfoDao attachmentFileInfoDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.userCrudOperation = userCrudOperation;
        this.ponbDao = ponbDao;
        this.ponbCache = ponbCache;
        this.noteBookCrudOperation = noteBookCrudOperation;
        this.noteBookDao = noteBookDao;
        this.noteNodeCrudOperation = noteNodeCrudOperation;
        this.noteNodeDao = noteNodeDao;
        this.noteItemCrudOperation = noteItemCrudOperation;
        this.noteItemDao = noteItemDao;
        this.attachmentFileInfoCrudOperation = attachmentFileInfoCrudOperation;
        this.attachmentFileInfoDao = attachmentFileInfoDao;
    }

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                userCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<PonbKey, Ponb> ponbGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                ponbDao,
                ponbCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                ponbTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Ponb> ponbDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                ponbDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Ponb> ponbDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                ponbDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteBook> noteBookBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                noteBookCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteBook> noteBookDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                noteBookDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteBook> noteBookDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                noteBookDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteNode> noteNodeBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                noteNodeCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteNode> noteNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                noteNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteNode> noteNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                noteNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteItem> noteItemBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                noteItemCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteItem> noteItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                noteItemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteItem> noteItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                noteItemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AttachmentFileInfo> attachmentFileInfoBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                attachmentFileInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AttachmentFileInfo> attachmentFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                attachmentFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AttachmentFileInfo> attachmentFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                attachmentFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
