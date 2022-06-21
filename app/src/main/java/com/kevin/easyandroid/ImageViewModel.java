package com.kevin.easyandroid;

import androidx.lifecycle.ViewModel;

import com.kevin.room_library.Group;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class ImageViewModel extends ViewModel {

    private final GroupDataSource mGroupDataSource;

    public ImageViewModel(GroupDataSource groupDataSource) {
        mGroupDataSource = groupDataSource;
    }

    public Flowable<List<Group>> getAllGroup() {
        return mGroupDataSource.getGroup();
    }

    public Completable insertOrUpdateGroup(Group group) {
        return mGroupDataSource.insertOrUpdateGroup(group);
    }

    public void deleteAllGroup(){
        mGroupDataSource.deleteAllGroup();
    }


}
