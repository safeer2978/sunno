package com.sunno.AuthModule.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sunno.AuthModule.data.model.User;


@Database(entities = {User.class}, version =1, exportSchema = false)
public abstract class LoginDatabase extends RoomDatabase{


    public abstract LoginDao wordDao();

    private static volatile  LoginDatabase INSTANCE;

    public static LoginDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (LoginDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LoginDatabase.class, "login_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
