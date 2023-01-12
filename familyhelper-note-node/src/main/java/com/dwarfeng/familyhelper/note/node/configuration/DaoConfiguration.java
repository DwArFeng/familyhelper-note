package com.dwarfeng.familyhelper.note.node.configuration;

import com.dwarfeng.familyhelper.note.impl.bean.entity.*;
import com.dwarfeng.familyhelper.note.impl.bean.key.HibernatePonbKey;
import com.dwarfeng.familyhelper.note.impl.dao.preset.*;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

    private final PonbPresetCriteriaMaker ponbPresetCriteriaMaker;
    private final NoteBookPresetCriteriaMaker noteBookPresetCriteriaMaker;
    private final NoteNodePresetCriteriaMaker noteNodePresetCriteriaMaker;
    private final NoteItemPresetCriteriaMaker noteItemPresetCriteriaMaker;
    private final AttachmentFileInfoPresetCriteriaMaker attachmentFileInfoPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            PonbPresetCriteriaMaker ponbPresetCriteriaMaker,
            NoteBookPresetCriteriaMaker noteBookPresetCriteriaMaker,
            NoteNodePresetCriteriaMaker noteNodePresetCriteriaMaker,
            NoteItemPresetCriteriaMaker noteItemPresetCriteriaMaker,
            AttachmentFileInfoPresetCriteriaMaker attachmentFileInfoPresetCriteriaMaker
    ) {
        this.template = template;
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
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, HibernateMapper.class),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<PonbKey, HibernatePonbKey, Ponb, HibernatePonb> ponbHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(PonbKey.class, HibernatePonbKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Ponb.class, HibernatePonb.class, HibernateMapper.class),
                HibernatePonb.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Ponb, HibernatePonb> ponbHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Ponb.class, HibernatePonb.class, HibernateMapper.class),
                HibernatePonb.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Ponb, HibernatePonb> ponbHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Ponb.class, HibernatePonb.class, HibernateMapper.class),
                HibernatePonb.class,
                ponbPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteBook, HibernateNoteBook>
    noteBookHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, HibernateMapper.class),
                HibernateNoteBook.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteBook, HibernateNoteBook> noteBookHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, HibernateMapper.class),
                HibernateNoteBook.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteBook, HibernateNoteBook> noteBookHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteBook.class, HibernateNoteBook.class, HibernateMapper.class),
                HibernateNoteBook.class,
                noteBookPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteNode, HibernateNoteNode>
    noteNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, HibernateMapper.class),
                HibernateNoteNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteNode, HibernateNoteNode> noteNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, HibernateMapper.class),
                HibernateNoteNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteNode, HibernateNoteNode> noteNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteNode.class, HibernateNoteNode.class, HibernateMapper.class),
                HibernateNoteNode.class,
                noteNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NoteItem, HibernateNoteItem>
    noteItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, HibernateMapper.class),
                HibernateNoteItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NoteItem, HibernateNoteItem> noteItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, HibernateMapper.class),
                HibernateNoteItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NoteItem, HibernateNoteItem> noteItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(NoteItem.class, HibernateNoteItem.class, HibernateMapper.class),
                HibernateNoteItem.class,
                noteItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AttachmentFileInfo, HibernateAttachmentFileInfo>
    attachmentFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, HibernateMapper.class
                ),
                HibernateAttachmentFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AttachmentFileInfo, HibernateAttachmentFileInfo>
    attachmentFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, HibernateMapper.class
                ),
                HibernateAttachmentFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AttachmentFileInfo, HibernateAttachmentFileInfo>
    attachmentFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        AttachmentFileInfo.class, HibernateAttachmentFileInfo.class, HibernateMapper.class
                ),
                HibernateAttachmentFileInfo.class,
                attachmentFileInfoPresetCriteriaMaker
        );
    }
}
