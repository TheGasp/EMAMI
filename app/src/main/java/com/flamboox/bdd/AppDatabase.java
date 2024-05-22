package com.flamboox.bdd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {
    public abstract CompteReseau compteReseau();
    public abstract RequeteEnCours requeteEnCours();
    public abstract Reseau reseau();
    public abstract User user();
}
