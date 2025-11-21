package com.example.jeudepart;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(columnName = "user_id", generatedId = true)
    private int user_id;

    @DatabaseField(columnName = "prenom", canBeNull = false)
    private String prenom;

    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;

    @DatabaseField(columnName = "email", unique = true, canBeNull = false)
    private String email;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "pays", dataType = DataType.ENUM_STRING, canBeNull = false)
    private Pays pays;

    public User() {
    }

    public User(String prenom, String nom, String email, String password, Pays pays) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.pays = pays;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}