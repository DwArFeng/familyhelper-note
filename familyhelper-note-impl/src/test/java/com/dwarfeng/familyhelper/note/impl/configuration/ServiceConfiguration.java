package com.dwarfeng.familyhelper.note.impl.configuration;

import com.dwarfeng.familyhelper.note.impl.service.operation.*;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.cache.FavoriteCache;
import com.dwarfeng.familyhelper.note.stack.cache.PonbCache;
import com.dwarfeng.familyhelper.note.stack.dao.*;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

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
    private final FavoriteDao favoriteDao;
    private final FavoriteCache favoriteCache;

    @Value("${cache.timeout.entity.ponb}")
    private long ponbTimeout;
    @Value("${cache.timeout.entity.favorite}")
    private long favoriteTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            UserCrudOperation userCrudOperation,
            PonbDao ponbDao,
            PonbCache ponbCache,
            NoteBookCrudOperation noteBookCrudOperation,
            NoteBookDao noteBookDao,
            NoteNodeCrudOperation noteNodeCrudOperation,
            NoteNodeDao noteNodeDao,
            NoteItemCrudOperation noteItemCrudOperation,
            NoteItemDao noteItemDao,
            AttachmentFileInfoCrudOperation attachmentFileInfoCrudOperation,
            AttachmentFileInfoDao attachmentFileInfoDao,
            FavoriteDao favoriteDao,
            FavoriteCache favoriteCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
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
        this.favoriteDao = favoriteDao;
        this.favoriteCache = favoriteCache;
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                userCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public GeneralBatchCrudService<PonbKey, Ponb> ponbGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                ponbDao,
                ponbCache,
                new ExceptionKeyGenerator<>(),
                ponbTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Ponb> ponbDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                ponbDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Ponb> ponbDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                ponbDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteBook> noteBookBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteBookCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteBook> noteBookDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteBookDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteBook> noteBookDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteBookDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteNode> noteNodeBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteNodeCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteNode> noteNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteNode> noteNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteNodeDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NoteItem> noteItemBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteItemCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NoteItem> noteItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteItemDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NoteItem> noteItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                noteItemDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AttachmentFileInfo> attachmentFileInfoBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                attachmentFileInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AttachmentFileInfo> attachmentFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                attachmentFileInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AttachmentFileInfo> attachmentFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                attachmentFileInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<FavoriteKey, Favorite> favoriteGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao,
                favoriteCache,
                new ExceptionKeyGenerator<>(),
                favoriteTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Favorite> favoriteDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Favorite> favoriteDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao
        );
    }
}
