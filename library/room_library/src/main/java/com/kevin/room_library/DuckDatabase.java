package com.kevin.room_library;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author : 王康
 * @date : 2022/6/21
 * @desc :
 */
@Database(entities = {Group.class}, version = 1)
public abstract class DuckDatabase extends RoomDatabase {

    private static volatile DuckDatabase INSTANCE;

    public abstract GroupDao groupDao();

    public static DuckDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DuckDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DuckDatabase.class, "collection-duck")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
