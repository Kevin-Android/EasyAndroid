package com.kevin.easyandroid;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {


    private final GroupDataSource mGroupDataSource;

    public MainViewModelFactory(GroupDataSource dataSource){
        mGroupDataSource = dataSource;
    }

    /**
     * 创建给定 {@code Class} 的新实例。
     * @param modelClass 一个{@code Class}，其实例被请求
     * @return 一个新创建的 ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mGroupDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
