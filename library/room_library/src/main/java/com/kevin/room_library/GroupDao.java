package com.kevin.room_library;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
@Dao
public interface GroupDao {

    @Query("SELECT * FROM `group`")
    Flowable<List<Group>> getAll();

    @Query("SELECT * FROM `group` WHERE gid IN (:ids)")
    List<Group> loadAllByIds(int[] ids);

    @Query("SELECT * FROM `group` WHERE group_name IN (:name) ")
    Group findByName(String name);

    @Insert
    Completable insertAll(Group... groups);

    @Delete
    void delete(Group group);

    @Query("delete from `group`")
    void deleteAll();
}
