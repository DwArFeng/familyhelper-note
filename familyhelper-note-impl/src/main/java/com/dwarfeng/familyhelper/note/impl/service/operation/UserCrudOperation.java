package com.dwarfeng.familyhelper.note.impl.service.operation;

import com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite;
import com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb;
import com.dwarfeng.familyhelper.note.stack.bean.entity.User;
import com.dwarfeng.familyhelper.note.stack.bean.key.FavoriteKey;
import com.dwarfeng.familyhelper.note.stack.bean.key.PonbKey;
import com.dwarfeng.familyhelper.note.stack.cache.FavoriteCache;
import com.dwarfeng.familyhelper.note.stack.cache.PonbCache;
import com.dwarfeng.familyhelper.note.stack.cache.UserCache;
import com.dwarfeng.familyhelper.note.stack.dao.FavoriteDao;
import com.dwarfeng.familyhelper.note.stack.dao.PonbDao;
import com.dwarfeng.familyhelper.note.stack.dao.UserDao;
import com.dwarfeng.familyhelper.note.stack.service.FavoriteMaintainService;
import com.dwarfeng.familyhelper.note.stack.service.PonbMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCrudOperation implements BatchCrudOperation<StringIdKey, User> {

    private final UserDao userDao;
    private final UserCache userCache;

    private final PonbDao ponbDao;
    private final PonbCache ponbCache;

    private final FavoriteDao favoriteDao;
    private final FavoriteCache favoriteCache;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;

    public UserCrudOperation(
            UserDao userDao,
            UserCache userCache,
            PonbDao ponbDao,
            PonbCache ponbCache,
            FavoriteDao favoriteDao,
            FavoriteCache favoriteCache
    ) {
        this.userDao = userDao;
        this.userCache = userCache;
        this.ponbDao = ponbDao;
        this.ponbCache = ponbCache;
        this.favoriteDao = favoriteDao;
        this.favoriteCache = favoriteCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return userCache.exists(key) || userDao.exists(key);
    }

    @Override
    public User get(StringIdKey key) throws Exception {
        if (userCache.exists(key)) {
            return userCache.get(key);
        } else {
            if (!userDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            User user = userDao.get(key);
            userCache.push(user, userTimeout);
            return user;
        }
    }

    @Override
    public StringIdKey insert(User user) throws Exception {
        userCache.push(user, userTimeout);
        return userDao.insert(user);
    }

    @Override
    public void update(User user) throws Exception {
        userCache.push(user, userTimeout);
        userDao.update(user);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与用户相关的个人最佳权限。
        List<PonbKey> ponbKeys = ponbDao.lookup(PonbMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Ponb::getKey).collect(Collectors.toList());
        ponbCache.batchDelete(ponbKeys);
        ponbDao.batchDelete(ponbKeys);

        // 删除与用户相关的个人最佳权限。
        List<FavoriteKey> favoriteKeys = favoriteDao.lookup(FavoriteMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Favorite::getKey).collect(Collectors.toList());
        favoriteCache.batchDelete(favoriteKeys);
        favoriteDao.batchDelete(favoriteKeys);

        // 删除用户实体自身。
        userCache.delete(key);
        userDao.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return userCache.allExists(keys) || userDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return userCache.nonExists(keys) && userDao.nonExists(keys);
    }

    @Override
    public List<User> batchGet(List<StringIdKey> keys) throws Exception {
        if (userCache.allExists(keys)) {
            return userCache.batchGet(keys);
        } else {
            if (!userDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<User> users = userDao.batchGet(keys);
            userCache.batchPush(users, userTimeout);
            return users;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        return userDao.batchInsert(users);
    }

    @Override
    public void batchUpdate(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        userDao.batchUpdate(users);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
