package com.example.jeudepart;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class UserDataSource {

    private static final String TAG = UserDataSource.class.getSimpleName();
    private Dao<User, Integer> userDao;
    private DatabaseHelper dbHelper;

    public UserDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);

        try {
            userDao = dbHelper.getDao(User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User createUser(User user) {
        try {
            int numRows = userDao.create(user);
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
    }
}