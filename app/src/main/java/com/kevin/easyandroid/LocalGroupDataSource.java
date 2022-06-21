package com.kevin.easyandroid;

import com.kevin.room_library.Group;
import com.kevin.room_library.GroupDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class LocalGroupDataSource implements GroupDataSource {

    private final GroupDao mGroupDao;

    public LocalGroupDataSource(GroupDao groupDao) {
        mGroupDao = groupDao;
    }

    @Override
    public Flowable<List<Group>> getGroup() {
        return mGroupDao.getAll();
    }

    @Override
    public Completable insertOrUpdateGroup(Group group) {
        return mGroupDao.insertAll(group);
    }

    @Override
    public void deleteAllGroup() {
        mGroupDao.deleteAll();
    }
}
