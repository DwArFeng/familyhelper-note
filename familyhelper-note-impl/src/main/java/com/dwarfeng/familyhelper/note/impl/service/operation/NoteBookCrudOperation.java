package com.dwarfeng.familyhelper.note.impl.service.operation;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.cache.NoteBookCache;
import com.dwarfeng.familyhelper.note.stack.cache.PonbCache;
import com.dwarfeng.familyhelper.note.stack.dao.NoteBookDao;
import com.dwarfeng.familyhelper.note.stack.dao.NoteItemDao;
import com.dwarfeng.familyhelper.note.stack.dao.NoteNodeDao;
import com.dwarfeng.familyhelper.note.stack.dao.PonbDao;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteNodeMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteBookCrudOperation implements BatchCrudOperation<LongIdKey, NoteBook> {

    private final NoteBookDao noteBookDao;
    private final NoteBookCache noteBookCache;

    private final PonbDao ponbDao;
    private final PonbCache ponbCache;

    private final NoteItemDao noteItemDao;
    private final NoteItemCrudOperation noteItemCrudOperation;

    private final NoteNodeDao noteNodeDao;
    private final NoteNodeCrudOperation noteNodeCrudOperation;

    @Value("${cache.timeout.entity.note_book}")
    private long noteBookTimeout;

    public NoteBookCrudOperation(
            NoteBookDao noteBookDao, NoteBookCache noteBookCache,
            PonbDao ponbDao, PonbCache ponbCache,
            NoteItemDao noteItemDao, NoteItemCrudOperation noteItemCrudOperation,
            NoteNodeDao noteNodeDao, NoteNodeCrudOperation noteNodeCrudOperation
    ) {
        this.noteBookDao = noteBookDao;
        this.noteBookCache = noteBookCache;
        this.ponbDao = ponbDao;
        this.ponbCache = ponbCache;
        this.noteItemDao = noteItemDao;
        this.noteItemCrudOperation = noteItemCrudOperation;
        this.noteNodeDao = noteNodeDao;
        this.noteNodeCrudOperation = noteNodeCrudOperation;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return noteBookCache.exists(key) || noteBookDao.exists(key);
    }

    @Override
    public NoteBook get(LongIdKey key) throws Exception {
        if (noteBookCache.exists(key)) {
            return noteBookCache.get(key);
        } else {
            if (!noteBookDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NoteBook noteBook = noteBookDao.get(key);
            noteBookCache.push(noteBook, noteBookTimeout);
            return noteBook;
        }
    }

    @Override
    public LongIdKey insert(NoteBook noteBook) throws Exception {
        noteBookCache.push(noteBook, noteBookTimeout);
        return noteBookDao.insert(noteBook);
    }

    @Override
    public void update(NoteBook noteBook) throws Exception {
        noteBookCache.push(noteBook, noteBookTimeout);
        noteBookDao.update(noteBook);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与个人最佳集合相关的个人最佳集合项目。
        List<LongIdKey> noteItemKeys = noteItemDao.lookup(
                NoteItemMaintainService.CHILD_FOR_BOOK, new Object[]{key}
        ).stream().map(NoteItem::getKey).collect(Collectors.toList());
        noteItemCrudOperation.batchDelete(noteItemKeys);

        // 删除与个人最佳集合相关的个人最佳集合节点。
        List<LongIdKey> noteNodeKeys = noteNodeDao.lookup(
                NoteNodeMaintainService.CHILD_FOR_BOOK_ROOT, new Object[]{key}
        ).stream().map(NoteNode::getKey).collect(Collectors.toList());
        noteNodeCrudOperation.batchDelete(noteNodeKeys);

        // 删除与个人最佳集合相关的个人最佳集合权限。
        List<PonbKey> ponbKeys = ponbDao.lookup(PonbMaintainService.CHILD_FOR_NOTE_BOOK, new Object[]{key})
                .stream().map(Ponb::getKey).collect(Collectors.toList());
        ponbCache.batchDelete(ponbKeys);
        ponbDao.batchDelete(ponbKeys);

        // 删除个人最佳集合实体自身。
        noteBookCache.delete(key);
        noteBookDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return noteBookCache.allExists(keys) || noteBookDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return noteBookCache.nonExists(keys) && noteBookDao.nonExists(keys);
    }

    @Override
    public List<NoteBook> batchGet(List<LongIdKey> keys) throws Exception {
        if (noteBookCache.allExists(keys)) {
            return noteBookCache.batchGet(keys);
        } else {
            if (!noteBookDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NoteBook> noteBooks = noteBookDao.batchGet(keys);
            noteBookCache.batchPush(noteBooks, noteBookTimeout);
            return noteBooks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NoteBook> noteBooks) throws Exception {
        noteBookCache.batchPush(noteBooks, noteBookTimeout);
        return noteBookDao.batchInsert(noteBooks);
    }

    @Override
    public void batchUpdate(List<NoteBook> noteBooks) throws Exception {
        noteBookCache.batchPush(noteBooks, noteBookTimeout);
        noteBookDao.batchUpdate(noteBooks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
