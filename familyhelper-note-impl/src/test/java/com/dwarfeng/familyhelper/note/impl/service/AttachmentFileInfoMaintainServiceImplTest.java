package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.service.AttachmentFileInfoMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AttachmentFileInfoMaintainServiceImplTest {

    @Autowired
    private AttachmentFileInfoMaintainService attachmentFileInfoMaintainService;
    @Autowired
    private NoteItemMaintainService noteItemMaintainService;

    private List<AttachmentFileInfo> attachmentFileInfos;
    private NoteItem noteItem;

    @Before
    public void setUp() {
        attachmentFileInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AttachmentFileInfo attachmentFileInfo = new AttachmentFileInfo(
                    null, null, "origin_name", 12450L, new Date(), new Date(), new Date(), "remark"
            );
            attachmentFileInfos.add(attachmentFileInfo);
        }
        noteItem = new NoteItem(null, null, null, "name", "remark", 12450L, new Date(), new Date(), new Date(), 12450);
    }

    @After
    public void tearDown() {
        attachmentFileInfos.clear();
        noteItem = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AttachmentFileInfo attachmentFileInfo : attachmentFileInfos) {
                attachmentFileInfo.setKey(attachmentFileInfoMaintainService.insert(attachmentFileInfo));

                AttachmentFileInfo testAttachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileInfo.getKey());
                assertEquals(BeanUtils.describe(attachmentFileInfo), BeanUtils.describe(testAttachmentFileInfo));
                attachmentFileInfoMaintainService.update(attachmentFileInfo);
                testAttachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileInfo.getKey());
                assertEquals(BeanUtils.describe(attachmentFileInfo), BeanUtils.describe(testAttachmentFileInfo));
            }
        } finally {
            for (AttachmentFileInfo attachmentFileInfo : attachmentFileInfos) {
                attachmentFileInfoMaintainService.deleteIfExists(attachmentFileInfo.getKey());
            }
        }
    }

    @Test
    public void testForNoteItemCascade() throws Exception {
        try {
            noteItem.setKey(noteItemMaintainService.insertOrUpdate(noteItem));
            for (AttachmentFileInfo attachmentFileInfo : attachmentFileInfos) {
                attachmentFileInfo.setNoteItemKey(noteItem.getKey());
                attachmentFileInfo.setKey(attachmentFileInfoMaintainService.insert(attachmentFileInfo));
            }

            assertEquals(attachmentFileInfos.size(), attachmentFileInfoMaintainService.lookup(
                    AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM, new Object[]{noteItem.getKey()}
            ).getCount());

            noteItemMaintainService.deleteIfExists(noteItem.getKey());

            assertEquals(0, attachmentFileInfoMaintainService.lookup(
                    AttachmentFileInfoMaintainService.CHILD_FOR_NOTE_ITEM, new Object[]{noteItem.getKey()}
            ).getCount());
        } finally {
            noteItemMaintainService.deleteIfExists(noteItem.getKey());
            for (AttachmentFileInfo attachmentFileInfo : attachmentFileInfos) {
                attachmentFileInfoMaintainService.deleteIfExists(attachmentFileInfo.getKey());
            }
        }
    }
}
