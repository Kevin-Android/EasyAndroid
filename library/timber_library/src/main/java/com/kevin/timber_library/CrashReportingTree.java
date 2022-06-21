package com.kevin.timber_library;

import android.util.Log;

import androidx.annotation.NonNull;

import timber.log.Timber;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public class CrashReportingTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable throwable) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        FakeCrashLibrary.log(priority, tag, message);

        if (throwable != null) {
            if (priority == Log.ERROR) {
                FakeCrashLibrary.logError(throwable);
            } else if (priority == Log.WARN) {
                FakeCrashLibrary.logWarning(throwable);
            }
        }
    }
}
