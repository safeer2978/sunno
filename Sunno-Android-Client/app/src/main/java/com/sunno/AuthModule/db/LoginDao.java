package com.sunno.AuthModule.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sunno.AuthModule.data.model.User;

import java.util.concurrent.Future;

@Dao
public interface LoginDao {
    @Insert
    long insertUser(User user);

    @Delete
    void dropTable(User user);

    @Query("select * from login_database")
    User getUser();


}
