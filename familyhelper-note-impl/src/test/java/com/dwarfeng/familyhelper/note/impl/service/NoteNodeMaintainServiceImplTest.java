package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
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
public class NoteNodeMaintainServiceImplTest {

    @Autowired
    private NoteNodeMaintainService noteNodeMaintainService;
    @Autowired
    private NoteBookMaintainService noteBookMaintainService;

    private List<NoteNode> noteNodes;
    private NoteNode parent;
    private NoteBook noteBook;

    @Before
    public void setUp() {
        noteNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NoteNode noteNode = new NoteNode(null, null, null, "name", "remark");
            noteNodes.add(noteNode);
        }
        parent = new NoteNode(null, null, null, "name", "remark");
        noteBook = new NoteBook(null, "name", "remark", new Date(), 0, new Date(), new Date());
    }

    @After
    public void tearDown() {
        noteNodes.clear();
        parent = null;
        noteBook = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (NoteNode noteNode : noteNodes) {
                noteNode.setKey(noteNodeMaintainService.insert(noteNode));

                NoteNode testNoteNode = noteNodeMaintainService.get(noteNode.getKey());
                assertEquals(BeanUtils.describe(noteNode), BeanUtils.describe(testNoteNode));
                noteNodeMaintainService.update(noteNode);
                testNoteNode = noteNodeMaintainService.get(noteNode.getKey());
                assertEquals(BeanUtils.describe(noteNode), BeanUtils.describe(testNoteNode));
            }
        } finally {
            for (NoteNode noteNode : noteNodes) {
                noteNodeMaintainService.deleteIfExists(noteNode.getKey());
            }
        }
    }

    @Test
    public void testForParentCascade() throws Exception {
        try {
            parent.setKey(noteNodeMaintainService.insertOrUpdate(parent));
            for (NoteNode noteNode : noteNodes) {
                noteNode.setParentKey(parent.getKey());
                noteNode.setKey(noteNodeMaintainService.insert(noteNode));
            }

            assertEquals(noteNodes.size(), noteNodeMaintainService.lookup(
                    NoteNodeMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());

            noteNodeMaintainService.deleteIfExists(parent.getKey());

            assertEquals(0, noteNodeMaintainService.lookup(
                    NoteNodeMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());
        } finally {
            noteNodeMaintainService.deleteIfExists(parent.getKey());
            for (NoteNode noteNode : noteNodes) {
                noteNodeMaintainService.deleteIfExists(noteNode.getKey());
            }
        }
    }

    @Test
    public void testForNoteBookCascade() throws Exception {
        try {
            noteBook.setKey(noteBookMaintainService.insertOrUpdate(noteBook));
            for (NoteNode noteNode : noteNodes) {
                noteNode.setBookKey(noteBook.getKey());
                noteNode.setKey(noteNodeMaintainService.insert(noteNode));
            }

            assertEquals(noteNodes.size(), noteNodeMaintainService.lookup(
                    NoteNodeMaintainService.CHILD_FOR_BOOK, new Object[]{noteBook.getKey()}
            ).getCount());

            noteBookMaintainService.deleteIfExists(noteBook.getKey());

            assertEquals(0, noteNodeMaintainService.lookup(
                    NoteNodeMaintainService.CHILD_FOR_BOOK, new Object[]{noteBook.getKey()}
            ).getCount());
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            for (NoteNode noteNode : noteNodes) {
                noteNodeMaintainService.deleteIfExists(noteNode.getKey());
            }
        }
    }
}
