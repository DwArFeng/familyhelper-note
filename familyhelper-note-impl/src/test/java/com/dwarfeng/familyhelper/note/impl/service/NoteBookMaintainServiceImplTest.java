package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
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
public class NoteBookMaintainServiceImplTest {

    @Autowired
    private NoteBookMaintainService noteBookMaintainService;

    private List<NoteBook> noteBooks;

    @Before
    public void setUp() {
        noteBooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NoteBook noteBook = new NoteBook(null, "name", "remark", new Date(), 0, new Date(), new Date());
            noteBooks.add(noteBook);
        }
    }

    @After
    public void tearDown() {
        noteBooks.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (NoteBook noteBook : noteBooks) {
                noteBook.setKey(noteBookMaintainService.insert(noteBook));

                NoteBook testNoteBook = noteBookMaintainService.get(noteBook.getKey());
                assertEquals(BeanUtils.describe(noteBook), BeanUtils.describe(testNoteBook));
                noteBookMaintainService.update(noteBook);
                testNoteBook = noteBookMaintainService.get(noteBook.getKey());
                assertEquals(BeanUtils.describe(noteBook), BeanUtils.describe(testNoteBook));
            }
        } finally {
            for (NoteBook noteBook : noteBooks) {
                noteBookMaintainService.deleteIfExists(noteBook.getKey());
            }
        }
    }
}
