package com.dwarfeng.familyhelper.note.impl.service;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.User;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.familyhelper.note.stack.service.FavoriteMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteBookMaintainService;
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
public class FavoriteMaintainServiceImplTest {

    private static final long NOTE_BOOK_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private NoteBookMaintainService noteBookMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private FavoriteMaintainService favoriteMaintainService;

    private NoteBook noteBook;
    private User user;
    private Favorite favorite;

    @Before
    public void setUp() {
        noteBook = new NoteBook(new LongIdKey(NOTE_BOOK_ID), "name", "remark", new Date(), 0, new Date(), new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        favorite = new Favorite(new FavoriteKey(NOTE_BOOK_ID, USER_ID), "remark");
    }

    @After
    public void tearDown() {
        noteBook = null;
        user = null;
        favorite = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);
            favoriteMaintainService.update(favorite);

            Favorite testFavorite = favoriteMaintainService.get(favorite.getKey());
            assertEquals(BeanUtils.describe(favorite), BeanUtils.describe(testFavorite));
            testFavorite = favoriteMaintainService.get(favorite.getKey());
            assertEquals(BeanUtils.describe(favorite), BeanUtils.describe(testFavorite));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }

    @Test
    public void testForNoteBookCascade() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);

            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            assertFalse(favoriteMaintainService.exists(favorite.getKey()));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            noteBookMaintainService.insertOrUpdate(noteBook);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(favoriteMaintainService.exists(favorite.getKey()));
        } finally {
            noteBookMaintainService.deleteIfExists(noteBook.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }
}
