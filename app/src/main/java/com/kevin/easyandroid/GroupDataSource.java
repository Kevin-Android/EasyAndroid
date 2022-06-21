package com.kevin.easyandroid;


import com.kevin.room_library.Group;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public interface GroupDataSource {

    Flowable<List<Group>> getGroup();

    Completable insertOrUpdateGroup(Group group);

    void deleteAllGroup();

}
