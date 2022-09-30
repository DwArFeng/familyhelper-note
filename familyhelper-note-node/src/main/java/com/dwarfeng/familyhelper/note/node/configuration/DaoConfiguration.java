package com.dwarfeng.familyhelper.note.node.configuration;

import com.dwarfeng.familyhelper.note.impl.bean.entity.*;
import com.dwarfeng.familyhelper.note.impl.bean.key.HibernatePonbKey;
import com.dwarfeng.familyhelper.note.impl.dao.preset.*;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;
    private final Mapper mapper;

    private final PonbPresetCriteriaMaker ponbPresetCriteriaMaker;
    private final NoteBookPresetCriteriaMaker noteBookPresetCriteriaMaker;
    private final NoteNodePresetCriteriaMaker noteNodePresetCriteriaMaker;
    private final NoteItemPresetCriteriaMaker noteItemPresetCriteriaMaker;
    private final AttachmentFileInfoPresetCriteriaMaker attachmentFileInfoPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            PonbPresetCriteriaMaker ponbPresetCriteriaMaker,
            NoteBookPresetCriteriaMaker noteBookPresetCriteriaMaker,
            NoteNodePresetCriteriaMaker noteNodePresetCriteriaMaker,
            NoteItemPresetCriteriaMaker noteItemPresetCriteriaMaker,
            AttachmentFileInfoPresetCriteriaMaker attachmentFileInfoPresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.ponbPresetCriteriaMaker = ponbPresetCriteriaMaker;
        this.noteBookPresetCriteriaMaker = noteBookPresetCriteriaMaker;
        this.noteNodePresetCriteriaMaker = noteNodePresetCriteriaMaker;
        this.noteItemPresetCriteriaMaker = noteItemPresetCriteriaMaker;
        this.attachmentFileInfoPresetCriteriaMaker = attachmentFileInfoPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<PonbKey, HibernatePonbKey, Ponb, HibernatePonb> ponbHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(PonbKey.class, HibernatePonbKey.class, mapper),
                new DozerBeanTransformer<>(Ponb.class, HibernatePonb.class, mapper),
                HibernatePonb.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Ponb, HibernatePonb> ponbHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Ponb.class, HibernatePonb.class, mapper),
                HibernatePonb.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Ponb, HibernatePonb> ponbHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Ponb.class, HibernatePonb.class, mapper),
                HibernatePonb.class,
                ponbPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteBook, HibernateNoteBook>
    noteBookHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, mapper),
                HibernateNoteBook.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteBook, HibernateNoteBook> noteBookHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, mapper),
                HibernateNoteBook.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteBook, HibernateNoteBook> noteBookHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, mapper),
                HibernateNoteBook.class,
                noteBookPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteNode, HibernateNoteNode>
    noteNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, mapper),
                HibernateNoteNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteNode, HibernateNoteNode> noteNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, mapper),
                HibernateNoteNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteNode, HibernateNoteNode> noteNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, mapper),
                HibernateNoteNode.class,
                noteNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteItem, HibernateNoteItem>
    noteItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, mapper),
                HibernateNoteItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteItem, HibernateNoteItem> noteItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, mapper),
                HibernateNoteItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteItem, HibernateNoteItem> noteItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, mapper),
                HibernateNoteItem.class,
                noteItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AttachmentFileInfo, HibernateAttachmentFileInfo>
    attachmentFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, mapper),
                HibernateAttachmentFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AttachmentFileInfo, HibernateAttachmentFileInfo> attachmentFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, mapper),
                HibernateAttachmentFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AttachmentFileInfo, HibernateAttachmentFileInfo> attachmentFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, mapper),
                HibernateAttachmentFileInfo.class,
                attachmentFileInfoPresetCriteriaMaker
        );
    }
}
