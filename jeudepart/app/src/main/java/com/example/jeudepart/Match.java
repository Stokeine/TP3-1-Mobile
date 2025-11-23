package com.example.jeudepart;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "matches")
public class Match {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "user_id", canBeNull = false, foreign = true, foreignColumnName = "user_id", foreignAutoCreate = true)
    private User user;

    @DatabaseField
    private int score;

    @DatabaseField
    private Date date;

    public Match() {
    }

    public Match(User user, int score, Date date) {
        this.user = user;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
