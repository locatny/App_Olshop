package com.example.girlnyshop.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT COUNT(*) FROM user")
    int getUserCount();

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    User getUserByCredentials(String email, String password);
}
