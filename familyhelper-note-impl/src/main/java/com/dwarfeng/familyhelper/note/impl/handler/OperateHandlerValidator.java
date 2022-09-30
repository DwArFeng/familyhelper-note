package com.dwarfeng.familyhelper.note.impl.handler;

import com.dwarfeng.familyhelper.note.sdk.util.Constants;
import com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem;
import com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.exception.*;
import com.dwarfeng.familyhelper.note.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class OperateHandlerValidator {

    private final UserMaintainService userMaintainService;
    private final PonbMaintainService ponbMaintainService;
    private final NoteBookMaintainService noteBookMaintainService;
    private final NoteNodeMaintainService noteNodeMaintainService;
    private final NoteItemMaintainService noteItemMaintainService;
    private final AttachmentFileInfoMaintainService attachmentFileInfoMaintainService;

    public OperateHandlerValidator(
            UserMaintainService userMaintainService,
            PonbMaintainService ponbMaintainService,
            NoteBookMaintainService noteBookMaintainService,
            NoteNodeMaintainService noteNodeMaintainService,
            NoteItemMaintainService noteItemMaintainService,
            AttachmentFileInfoMaintainService attachmentFileInfoMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.ponbMaintainService = ponbMaintainService;
        this.noteBookMaintainService = noteBookMaintainService;
        this.noteNodeMaintainService = noteNodeMaintainService;
        this.noteItemMaintainService = noteItemMaintainService;
        this.attachmentFileInfoMaintainService = attachmentFileInfoMaintainService;
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNoteBookExists(LongIdKey noteBookKey) throws HandlerException {
        try {
            if (Objects.isNull(noteBookKey) || !noteBookMaintainService.exists(noteBookKey)) {
                throw new NoteBookNotExistsException(noteBookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNoteNodeExists(LongIdKey noteNodeKey) throws HandlerException {
        try {
            if (Objects.isNull(noteNodeKey) || !noteNodeMaintainService.exists(noteNodeKey)) {
                throw new NoteNodeNotExistsException(noteNodeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNoteItemExists(LongIdKey noteItemKey) throws HandlerException {
        try {
            if (Objects.isNull(noteItemKey) || !noteItemMaintainService.exists(noteItemKey)) {
                throw new NoteItemNotExistsException(noteItemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAttachmentFileInfoExists(LongIdKey attachmentFileKey) throws HandlerException {
        try {
            if (Objects.isNull(attachmentFileKey) || !attachmentFileInfoMaintainService.exists(attachmentFileKey)) {
                throw new AttachmentFileInfoNotExistsException(attachmentFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserInspectPermittedForNoteBook(StringIdKey userKey, LongIdKey noteBookKey)
            throws HandlerException {
        try {
            // 1. 构造 Ponb 主键。
            PonbKey ponbKey = new PonbKey(noteBookKey.getLongId(), userKey.getStringId());

            // 2. 查看 Ponb 实体是否存在，如果不存在，则没有权限。
            if (!ponbMaintainService.exists(ponbKey)) {
                throw new UserNotPermittedForNoteBookException(userKey, noteBookKey);
            }

            // 3. 查看 Ponb.permissionLevel 是否为 Ponb.PERMISSION_LEVEL_OWNER 或 Ponb.PERMISSION_LEVEL_GUEST，
            // 如果不是，则没有权限。
            Ponb ponb = ponbMaintainService.get(ponbKey);
            if (Objects.equals(ponb.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                return;
            }
            if (Objects.equals(ponb.getPermissionLevel(), Constants.PERMISSION_LEVEL_GUEST)) {
                return;
            }
            throw new UserNotPermittedForNoteBookException(userKey, noteBookKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserModifyPermittedForNoteBook(StringIdKey userKey, LongIdKey noteBookKey)
            throws HandlerException {
        try {
            // 1. 构造 Ponb 主键。
            PonbKey ponbKey = new PonbKey(noteBookKey.getLongId(), userKey.getStringId());

            // 2. 查看 Ponb 实体是否存在，如果不存在，则没有权限。
            if (!ponbMaintainService.exists(ponbKey)) {
                throw new UserNotPermittedForNoteBookException(userKey, noteBookKey);
            }

            // 3. 查看 Ponb.permissionLevel 是否为 Ponb.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Ponb ponb = ponbMaintainService.get(ponbKey);
            if (Objects.equals(ponb.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                return;
            }
            throw new UserNotPermittedForNoteBookException(userKey, noteBookKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNoteBookIdenticalForNoteBook(LongIdKey nodeKey, LongIdKey bookKey)
            throws HandlerException {
        try {
            // 如果 leftNodeKey 为 null，则表示该项目是根项目，不需要进行任何判断。
            if (Objects.isNull(nodeKey)) {
                return;
            }

            NoteNode parentNode = noteNodeMaintainService.get(nodeKey);
            LongIdKey parentSetKey = parentNode.getBookKey();
            if (Objects.isNull(parentSetKey)) {
                throw new IllegalNoteNodeStateException(nodeKey);
            }
            if (!Objects.equals(parentSetKey, bookKey)) {
                throw new NoteBookNotIdenticalException(parentSetKey, bookKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserInspectPermittedForNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws HandlerException {
        try {
            // 1. 查找指定的个人最佳节点是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳节点状态异常。
            NoteNode noteNode = noteNodeMaintainService.get(noteNodeKey);
            if (Objects.isNull(noteNode.getBookKey())) {
                throw new IllegalNoteNodeStateException(noteNodeKey);
            }

            // 2. 取出个人最佳节点的个人最佳集合外键，判断用户是否拥有该个人最佳集合的权限。
            makeSureUserInspectPermittedForNoteBook(userKey, noteNode.getBookKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserModifyPermittedForNoteNode(StringIdKey userKey, LongIdKey noteNodeKey) throws HandlerException {
        try {
            // 1. 查找指定的个人最佳节点是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳节点状态异常。
            NoteNode noteNode = noteNodeMaintainService.get(noteNodeKey);
            if (Objects.isNull(noteNode.getBookKey())) {
                throw new IllegalNoteNodeStateException(noteNodeKey);
            }

            // 2. 取出个人最佳节点的个人最佳集合外键，判断用户是否拥有该个人最佳集合的权限。
            makeSureUserModifyPermittedForNoteBook(userKey, noteNode.getBookKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserInspectPermittedForNoteItem(StringIdKey userKey, LongIdKey noteItemKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳项目是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳项目状态异常。
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            if (Objects.isNull(noteItem.getBookKey())) {
                throw new IllegalNoteItemStateException(noteItemKey);
            }

            // 2. 取出个人最佳项目的个人最佳集合外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserInspectPermittedForNoteBook(userKey, noteItem.getBookKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserModifyPermittedForNoteItem(StringIdKey userKey, LongIdKey noteItemKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳项目是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳项目状态异常。
            NoteItem noteItem = noteItemMaintainService.get(noteItemKey);
            if (Objects.isNull(noteItem.getBookKey())) {
                throw new IllegalNoteItemStateException(noteItemKey);
            }

            // 2. 取出个人最佳项目的个人最佳集合外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserModifyPermittedForNoteBook(userKey, noteItem.getBookKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserInspectPermittedForAttachmentFileInfo(StringIdKey userKey, LongIdKey attachmentFileInfoKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳记录是否绑定个人最佳节点，如果不绑定个人最佳节点，则抛出个人最佳记录状态异常。
            AttachmentFileInfo attachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileInfoKey);
            if (Objects.isNull(attachmentFileInfo.getNoteItemKey())) {
                throw new IllegalAttachmentFileInfoStateException(attachmentFileInfoKey);
            }

            // 2. 取出个人最佳记录的个人最佳节点外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserInspectPermittedForNoteItem(userKey, attachmentFileInfo.getNoteItemKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserModifyPermittedForAttachmentFileInfo(StringIdKey userKey, LongIdKey attachmentFileInfoKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳记录是否绑定个人最佳节点，如果不绑定个人最佳节点，则抛出个人最佳记录状态异常。
            AttachmentFileInfo attachmentFileInfo = attachmentFileInfoMaintainService.get(attachmentFileInfoKey);
            if (Objects.isNull(attachmentFileInfo.getNoteItemKey())) {
                throw new IllegalAttachmentFileInfoStateException(attachmentFileInfoKey);
            }

            // 2. 取出个人最佳记录的个人最佳节点外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserModifyPermittedForNoteItem(userKey, attachmentFileInfo.getNoteItemKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNoteBookIdenticalForNoteNode(LongIdKey leftNodeKey, LongIdKey rightNodeKey)
            throws HandlerException {
        try {
            NoteNode childNode = noteNodeMaintainService.get(rightNodeKey);
            LongIdKey childSetKey = childNode.getBookKey();
            if (Objects.isNull(childSetKey)) {
                throw new IllegalNoteNodeStateException(leftNodeKey);
            }
            makeSureNoteBookIdenticalForNoteBook(leftNodeKey, childSetKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
