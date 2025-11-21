package com.example.jeudepart;

import androidx.annotation.DrawableRes;

public enum Pays {
    CANADA("Canada", R.drawable.can),
    BRESIL("Bresil", R.drawable.bra),
    ALLEMAGNE("Allemagne", R.drawable.ger),
    NORVEGE("Norvege", R.drawable.nor),
    TURQUIE("Turquie", R.drawable.tur),
    MEXIQUE("Mexique", R.drawable.mex),
    USA("usa", R.drawable.usa),
    ESPAGNE("Espagne", R.drawable.esp),
    AUSTRALIE("Australie", R.drawable.aus),
    DANEMARK("Danemark", R.drawable.den);


    private String nom;
    @DrawableRes
    private final int drapeauResId;

    Pays(String nom, @DrawableRes int drapeauResId) {
        this.drapeauResId = drapeauResId;
        this.nom = nom;
    }

    @DrawableRes
    public int getDrapeauResId() {
        return drapeauResId;
    }

    public String getNom() {
        return nom;
    }
    }
