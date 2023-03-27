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
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
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
        // 寻找自身的子孙节点和子孙条目。
        DescendantStruct descendantStruct = findDescendant(key);

        // 依次删除子孙条目和子孙节点。
        List<LongIdKey> noteItemKeys = descendantStruct.getNoteItemKeys();
        noteItemCrudOperation.batchDelete(noteItemKeys);
        List<LongIdKey> noteNodeKeys = descendantStruct.getNoteNodeKeys();
        noteNodeCache.batchDelete(noteNodeKeys);
        noteNodeDao.batchDelete(noteNodeKeys);
    }

    private DescendantStruct findDescendant(LongIdKey key) throws Exception {
        // 本方法使用递归形式，并转化为迭代。

        // 定义结果变量。
        List<LongIdKey> noteNodeKeys = new LinkedList<>();
        List<LongIdKey> noteItemKeys = new ArrayList<>();

        // 定义一个栈，并初始化。
        Stack<LongIdKey> noteNodeStack = new Stack<>();
        noteNodeStack.push(key);

        // 在栈清空之前，一直执行循环。
        while (!noteNodeStack.isEmpty()) {
            // 从栈中取出当前的节点。
            LongIdKey noteNodeKey = noteNodeStack.pop();
            // 查询节点的子节点。
            List<LongIdKey> childNoteNodeKeys = noteNodeDao.lookup(
                    NoteNodeMaintainService.CHILD_FOR_PARENT, new Object[]{noteNodeKey}
            ).stream().map(NoteNode::getKey).collect(Collectors.toList());
            // 查询节点的子条目。
            List<LongIdKey> childNoteItemKeys = noteItemDao.lookup(
                    NoteItemMaintainService.CHILD_FOR_NODE, new Object[]{noteNodeKey}
            ).stream().map(NoteItem::getKey).collect(Collectors.toList());
            // 将结果添加到结果变量中（插入到最前面）。
            noteNodeKeys.add(0, noteNodeKey);
            noteItemKeys.addAll(childNoteItemKeys);
            // 向栈中推送节点的子节点。
            childNoteNodeKeys.forEach(noteNodeStack::push);
        }

        // 返回结果。
        return new DescendantStruct(noteNodeKeys, noteItemKeys);
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

    private static final class DescendantStruct {

        private final List<LongIdKey> noteNodeKeys;
        private final List<LongIdKey> noteItemKeys;

        private DescendantStruct(List<LongIdKey> noteNodeKeys, List<LongIdKey> noteItemKeys) {
            this.noteNodeKeys = noteNodeKeys;
            this.noteItemKeys = noteItemKeys;
        }

        public List<LongIdKey> getNoteNodeKeys() {
            return noteNodeKeys;
        }

        public List<LongIdKey> getNoteItemKeys() {
            return noteItemKeys;
        }

        @Override
        public String toString() {
            return "DescendantStruct{" +
                    "noteNodeKeys=" + noteNodeKeys +
                    ", noteItemKeys=" + noteItemKeys +
                    '}';
        }
    }
}
