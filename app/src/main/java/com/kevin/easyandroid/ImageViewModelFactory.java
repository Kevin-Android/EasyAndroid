package com.kevin.easyandroid;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author : 王康
 * @date : 2022/6/22
 * @desc :
 */
public class ImageViewModelFactory implements ViewModelProvider.Factory  {
    private final GroupDataSource mGroupDataSource;

    public ImageViewModelFactory(GroupDataSource dataSource){
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
        if (modelClass.isAssignableFrom(ImageViewModel.class)){
            return (T) new ImageViewModel(mGroupDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
