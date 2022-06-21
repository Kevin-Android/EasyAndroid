package com.kevin.timber_library;

import android.os.Build;

import androidx.annotation.NonNull;

import timber.log.Timber;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
public final class DebugLoggerTree extends Timber.DebugTree {

    private static final int MAX_TAG_LENGTH = 23;

    @Override
    protected String createStackElementTag(@NonNull StackTraceElement element) {
        String tag = "(" + element.getFileName() + ":" + element.getLineNumber() + ")";
        // 日志 TAG 长度限制已经在 Android 8.0 被移除
        if (tag.length() <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return tag;
        }
        return tag.substring(0, MAX_TAG_LENGTH);
    }
}
