package com.kevin.easyandroid;

import android.app.Application;


import com.kevin.timber_library.DebugLoggerTree;

import timber.log.Timber;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class DuckApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugLoggerTree());
        }
    }
}
