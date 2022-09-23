package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteNodeMaintainService;
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
public class NoteItemMaintainServiceImplTest {

    @Autowired
    private NoteItemMaintainService noteItemMaintainService;
    @Autowired
    private NoteNodeMaintainService noteNodeMaintainService;
    @Autowired
    private NoteBookMaintainService noteBookMaintainService;

    private List<NoteItem> noteItems;
    private NoteNode noteNode;
    private NoteBook noteBook;

    @Before
    public void setUp() {
        noteItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NoteItem noteItem = new NoteItem(
                    null, null, null, "name", "remark", 12450L, new Date(), new Date(), new Date(), 12450
            );
            noteItems.add(noteItem);
        }
        noteNode = new NoteNode(null, null, null, "name", "remark");
        noteBook = new NoteBook(null, "name", "remark", new Date(), 0, new Date(), new Date());
    }

    @After
    public void tearDown() {
        noteItems.clear();
        noteNode = null;
        noteBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (NoteItem noteItem : noteItems) {
                noteItem.setKey(noteItemMaintainService.insert(noteItem));

                NoteItem testNoteItem = noteItemMaintainService.get(noteItem.getKey());
                assertEquals(BeanUtils.describe(noteItem), BeanUtils.describe(testNoteItem));
                noteItemMaintainService.update(noteItem);
                testNoteItem = noteItemMaintainService.get(noteItem.getKey());
                assertEquals(BeanUtils.describe(noteItem), BeanUtils.describe(testNoteItem));
            }
        } finally {
            for (NoteItem noteItem : noteItems) {
                noteItemMaintainService.deleteIfExists(noteItem.getKey());
            }
        }
    }

    @Test
    public void testForNoteNodeCascade() throws Exception {
        try {
            noteNode.setKey(noteNodeMaintainService.insertOrUpdate(noteNode));
            for (NoteItem noteItem : noteItems) {
                noteItem.setNodeKey(noteNode.getKey());
                noteItem.setKey(noteItemMaintainService.insert(noteItem));
            }

            assertEquals(noteItems.size(), noteItemMaintainService.lookup(
                    NoteItemMaintainService.CHILD_FOR_NODE, new Object[]{noteNode.getKey()}
            ).getCount());

            noteNodeMaintainService.deleteIfExists(noteNode.getKey());

            assertEquals(0, noteItemMaintainService.lookup(
                    NoteItemMaintainService.CHILD_FOR_NODE, new Object[]{noteNode.getKey()}
            ).getCount());
        } finally {
            noteNodeMaintainService.deleteIfExists(noteNode.getKey());
            for (NoteItem noteItem : noteItems) {
                noteItemMaintainService.deleteIfExists(noteItem.getKey());
            }
        }
    }

    @Test
    public void testForNoteBookCascade() throws Exception {
        try {
            noteBook.setKey(noteBookMaintainService.insertOrUpdate(noteBook));
            for (NoteItem noteItem : noteItems) {
                noteItem.setBookKey(noteBook.getKey());
                noteItem.setKey(noteItemMaintainService.insert(noteItem));
            }

            assertEquals(noteItems.size(), noteItemMaintainService.lookup(
                    NoteItemMaintainService.CHILD_FOR_BOOK, new Object[]{noteBook.getKey()}
            ).getCount());

            noteBookMaintainService.deleteIfExists(noteBook.getKey());

            assertEquals(0, noteItemMaintainService.lookup(
                    NoteItemMaintainService.CHILD_FOR_BOOK, new Object[]{noteBook.getKey()}
            ).getCount());
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            for (NoteItem noteItem : noteItems) {
                noteItemMaintainService.deleteIfExists(noteItem.getKey());
            }
        }
    }
}
