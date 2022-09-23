package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.entity.User;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PonbMaintainServiceImplTest {

    private static final long NOTE_BOOK_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private NoteBookMaintainService noteBookMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PonbMaintainService ponbMaintainService;

    private NoteBook noteBook;
    private User user;
    private Ponb ponb;

    @Before
    public void setUp() {
        noteBook = new NoteBook(new LongIdKey(NOTE_BOOK_ID), "name", "remark", new Date(), 0, new Date(), new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        ponb = new Ponb(new PonbKey(NOTE_BOOK_ID, USER_ID), 233, "remark");
    }

    @After
    public void tearDown() {
        noteBook = null;
        user = null;
        ponb = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            ponbMaintainService.insert(ponb);
            ponbMaintainService.update(ponb);

            Ponb testPonb = ponbMaintainService.get(ponb.getKey());
            assertEquals(BeanUtils.describe(ponb), BeanUtils.describe(testPonb));
            testPonb = ponbMaintainService.get(ponb.getKey());
            assertEquals(BeanUtils.describe(ponb), BeanUtils.describe(testPonb));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            ponbMaintainService.deleteIfExists(ponb.getKey());
        }
    }

    @Test
    public void testForNoteBookCascade() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            ponbMaintainService.insert(ponb);

            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            assertFalse(ponbMaintainService.exists(ponb.getKey()));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            ponbMaintainService.deleteIfExists(ponb.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            ponbMaintainService.insert(ponb);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(ponbMaintainService.exists(ponb.getKey()));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            ponbMaintainService.deleteIfExists(ponb.getKey());
        }
    }
}
