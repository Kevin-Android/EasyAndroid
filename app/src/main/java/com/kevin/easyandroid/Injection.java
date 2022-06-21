package com.kevin.easyandroid;

import android.content.Context;

import com.kevin.easyandroid.ui.fragment.ImageFragment;
import com.kevin.room_library.DuckDatabase;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class Injection {


    public static GroupDataSource provideUserDataSource(Context context) {
        DuckDatabase database = DuckDatabase.getInstance(context);
        return new LocalGroupDataSource(database.groupDao());
    }

    public static MainViewModelFactory provideViewModelFactory(Context context) {
        GroupDataSource dataSource = provideUserDataSource(context);
        return new MainViewModelFactory(dataSource);
    }

    public static ImageViewModelFactory provideImageViewModelFactory(Context context) {
        GroupDataSource dataSource = provideUserDataSource(context);
        return new ImageViewModelFactory(dataSource);
    }

}
