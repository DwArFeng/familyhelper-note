package com.dwarfeng.familyhelper.note.node.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.familyhelper.note.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonUser.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonPonb.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonNoteBook.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonNoteNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonNoteItem.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonAttachmentFileInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFavorite.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
