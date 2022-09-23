package com.dwarfeng.familyhelper.note.impl.service.operation;

import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.cache.NoteNodeCache;
import com.dwarfeng.familyhelper.note.stack.dao.NoteItemDao;
import com.dwarfeng.familyhelper.note.stack.dao.NoteNodeDao;
import com.dwarfeng.familyhelper.note.stack.service.NoteItemMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.NoteNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteNodeCrudOperation implements BatchCrudOperation<LongIdKey, NoteNode> {

    private final NoteNodeDao noteNodeDao;
    private final NoteNodeCache noteNodeCache;

    private final NoteItemCrudOperation noteItemCrudOperation;
    private final NoteItemDao noteItemDao;

    @Value("${cache.timeout.entity.note_node}")
    private long noteNodeTimeout;

    public NoteNodeCrudOperation(
            NoteNodeDao noteNodeDao, NoteNodeCache noteNodeCache,
            NoteItemCrudOperation noteItemCrudOperation, NoteItemDao noteItemDao
    ) {
        this.noteNodeDao = noteNodeDao;
        this.noteNodeCache = noteNodeCache;
        this.noteItemCrudOperation = noteItemCrudOperation;
        this.noteItemDao = noteItemDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return noteNodeCache.exists(key) || noteNodeDao.exists(key);
    }

    @Override
    public NoteNode get(LongIdKey key) throws Exception {
        if (noteNodeCache.exists(key)) {
            return noteNodeCache.get(key);
        } else {
            if (!noteNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NoteNode noteNode = noteNodeDao.get(key);
            noteNodeCache.push(noteNode, noteNodeTimeout);
            return noteNode;
        }
    }

    @Override
    public LongIdKey insert(NoteNode noteNode) throws Exception {
        noteNodeCache.push(noteNode, noteNodeTimeout);
        return noteNodeDao.insert(noteNode);
    }

    @Override
    public void update(NoteNode noteNode) throws Exception {
        noteNodeCache.push(noteNode, noteNodeTimeout);
        noteNodeDao.update(noteNode);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 递归寻找并删除个人最佳节点自身的子孙节点。
        List<NoteNode> descendantNoteNodes = new ArrayList<>();
        findDescendant(descendantNoteNodes, noteNodeDao.get(key));
        descendantNoteNodes.forEach((noteNode -> noteNode.setParentKey(null)));
        noteNodeDao.batchUpdate(descendantNoteNodes);
        List<LongIdKey> descendantNoteNodeKeys = descendantNoteNodes.stream().map(NoteNode::getKey).collect(Collectors.toList());
        noteNodeCache.batchDelete(descendantNoteNodeKeys);
        noteNodeDao.batchDelete(descendantNoteNodeKeys);

        // 查找删除除所有相关的个人最佳项目。
        List<LongIdKey> noteItemKeys = noteItemDao.lookup(
                NoteItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(NoteItem::getKey).collect(Collectors.toList());
        noteItemCrudOperation.batchDelete(noteItemKeys);

        // 删除个人最佳节点自身。
        noteNodeCache.delete(key);
        noteNodeDao.delete(key);
    }

    private void findDescendant(List<NoteNode> descendantNoteNodeKeys, NoteNode noteNode) throws Exception {
        List<NoteNode> childNoteNodes = noteNodeDao.lookup(NoteNodeMaintainService.CHILD_FOR_PARENT, new Object[]{noteNode.getKey()});
        for (NoteNode childNoteNode : childNoteNodes) {
            descendantNoteNodeKeys.add(childNoteNode);
            findDescendant(descendantNoteNodeKeys, childNoteNode);
        }
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return noteNodeCache.allExists(keys) || noteNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return noteNodeCache.nonExists(keys) && noteNodeDao.nonExists(keys);
    }

    @Override
    public List<NoteNode> batchGet(List<LongIdKey> keys) throws Exception {
        if (noteNodeCache.allExists(keys)) {
            return noteNodeCache.batchGet(keys);
        } else {
            if (!noteNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NoteNode> noteNodes = noteNodeDao.batchGet(keys);
            noteNodeCache.batchPush(noteNodes, noteNodeTimeout);
            return noteNodes;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NoteNode> noteNodes) throws Exception {
        noteNodeCache.batchPush(noteNodes, noteNodeTimeout);
        return noteNodeDao.batchInsert(noteNodes);
    }

    @Override
    public void batchUpdate(List<NoteNode> noteNodes) throws Exception {
        noteNodeCache.batchPush(noteNodes, noteNodeTimeout);
        noteNodeDao.batchUpdate(noteNodes);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
