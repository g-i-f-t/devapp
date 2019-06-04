package com.example.activity_maim.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.activity_maim.User;


//DB테이블 생성을 해라!
// User에서 entity 스키마를  해당 데이터베이스에 구축
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao roomUserDao();

    private static AppDatabase instance;
    private static final Object info = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (info){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "Users.db").fallbackToDestructiveMigration().build();
            }
            return instance;
        }
    }


}
