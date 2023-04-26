package com.dwarfeng.familyhelper.note.impl.configuration;

import com.dwarfeng.familyhelper.note.sdk.bean.FastJsonMapper;
import com.dwarfeng.familyhelper.note.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.note.sdk.bean.key.formatter.PonbStringKeyFormatter;
import com.dwarfeng.familyhelper.note.stack.bean.entity.*;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.ponb}")
    private String ponbPrefix;
    @Value("${cache.prefix.entity.note_book}")
    private String noteBookPrefix;
    @Value("${cache.prefix.entity.note_node}")
    private String noteNodePrefix;
    @Value("${cache.prefix.entity.note_item}")
    private String noteItemPrefix;
    @Value("${cache.prefix.entity.attachment_file_info}")
    private String attachmentFileInfoPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PonbKey, Ponb, FastJsonPonb> ponbRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPonb>) template,
                new PonbStringKeyFormatter(ponbPrefix),
                new MapStructBeanTransformer<>(Ponb.class, FastJsonPonb.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NoteBook, FastJsonNoteBook> noteBookRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNoteBook>) template,
                new LongIdStringKeyFormatter(noteBookPrefix),
                new MapStructBeanTransformer<>(NoteBook.class, FastJsonNoteBook.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NoteNode, FastJsonNoteNode> noteNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNoteNode>) template,
                new LongIdStringKeyFormatter(noteNodePrefix),
                new MapStructBeanTransformer<>(NoteNode.class, FastJsonNoteNode.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NoteItem, FastJsonNoteItem> noteItemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNoteItem>) template,
                new LongIdStringKeyFormatter(noteItemPrefix),
                new MapStructBeanTransformer<>(NoteItem.class, FastJsonNoteItem.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AttachmentFileInfo, FastJsonAttachmentFileInfo>
    attachmentFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAttachmentFileInfo>) template,
                new LongIdStringKeyFormatter(attachmentFileInfoPrefix),
                new MapStructBeanTransformer<>(
                        AttachmentFileInfo.class, FastJsonAttachmentFileInfo.class, FastJsonMapper.class
                )
        );
    }
}
