package com.example.jeudepart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "game_db.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Match.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Match.class, true);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() {
        super.close();
    }


    public List<Match> readMatch(){
        try{
            Dao<Match, Integer> dao = getDao(Match.class);
            List<Match> matches = dao.queryForAll();
            return matches;

        }catch (Exception e){
            return new ArrayList<>();
        }
    }


    public List<Match> readMatches(User user) {
        try {
            Dao<Match, Integer> dao = getDao(Match.class);
            QueryBuilder<Match, Integer> queryBuilder = dao.queryBuilder();
            Where<Match, Integer> where = queryBuilder.where();
            where.eq("user_id", user.getUser_id());
            List<Match> matches = queryBuilder.query();
            return matches;
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    public List<User> readUsers(){
        try{
            Dao<User, Integer> dao = getDao(User.class);
            List<User> users = dao.queryForAll();
            return users;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public boolean insertMatch(Match match) {
        boolean result = true;
        try {
            Dao<Match, Integer> dao = getDao(Match.class);
            dao.create(match);
        } catch (Exception exception) {
            result = false;
        }

        return result;
    }

    public User readUser(String email, String password) {
        try {
            Dao<User, Integer> dao = getDao(User.class);
            QueryBuilder<User, Integer> queryBuilder = dao.queryBuilder();
            Where<User, Integer> where = queryBuilder.where();
            where.eq("email", email);
            where.and();
            where.eq("password", password);
            List<User> user = queryBuilder.query();
            return user.get(0);
        } catch (Exception exception) {
            return null;
        }
    }
}