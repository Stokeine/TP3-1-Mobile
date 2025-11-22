package com.example.jeudepart;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

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
            Log.e(TAG, "Erreur lors de la création de l'utilisateur: " + user.getEmail(), e);
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            QueryBuilder<User, Integer> queryBuilder = userDao.queryBuilder();
            queryBuilder.where().eq("email", email);
            return queryBuilder.queryForFirst();
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