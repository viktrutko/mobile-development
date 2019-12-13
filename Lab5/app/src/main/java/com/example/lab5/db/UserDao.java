package com.example.lab5.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT id, firstName, lastName, birthday FROM user " +
            "WHERE firstName LIKE '%' || :first || '%' AND lastName LIKE '%' || :last || '%'")
    List<User> getFirstAndLastNameOrPartOfThem(String first, String last);

    @Query("SELECT id, firstName, lastName, birthday FROM user " +
            "WHERE lastName LIKE '%' || :last || '%'")
    List<User> getLastNameOrPartOfThem(String last);

    @Query("SELECT id, firstName, lastName, birthday FROM user " +
            "WHERE firstName LIKE '%' || :first || '%'")
    List<User> getFirstNameOrPartOfThem(String first);

    @Insert
    void insert(User user);
}
