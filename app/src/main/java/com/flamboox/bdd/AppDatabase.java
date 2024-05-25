package com.flamboox.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "emami.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_RESEAU = "RESEAU";
    public static final String TABLE_COMPTE_RESEAU = "COMPTE_RESEAU";
    public static final String TABLE_USER_OFFLINE = "USER_OFFLINE";

    public static final String COL_URL = "url";
    public static final String COL_DENOMINATION = "denomination";
    public static final String COL_ADRESSE_LOGO = "adresseLogo";

    public static final String COL_ID_COMPTE = "idCompte";
    public static final String COL_URL_RESEAU = "urlReseau";
    public static final String COL_MAIL_USER = "mailUser";

    public static final String COL_MAIL = "mail";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_NUM_TEL = "numTel";
    public static final String COL_ADRESSE_PHOTO = "adressePhoto";
    public static final String COL_STATUT = "statut";

    private static final String CREATE_TABLE_RESEAU = "CREATE TABLE " + TABLE_RESEAU + " (" +
            COL_URL + " VARCHAR(255) PRIMARY KEY, " +
            COL_DENOMINATION + " VARCHAR(255) NOT NULL UNIQUE, " +
            COL_ADRESSE_LOGO + " VARCHAR(255));";

    private static final String CREATE_TABLE_COMPTE_RESEAU = "CREATE TABLE " + TABLE_COMPTE_RESEAU + " (" +
            COL_ID_COMPTE + " VARCHAR(255) PRIMARY KEY, " +
            COL_URL_RESEAU + " VARCHAR(255), " +
            COL_MAIL_USER + " VARCHAR(255) NOT NULL, " +
            "FOREIGN KEY (" + COL_URL_RESEAU + ") REFERENCES " + TABLE_RESEAU + "(" + COL_URL + "), " +
            "FOREIGN KEY (" + COL_MAIL_USER + ") REFERENCES " + TABLE_USER_OFFLINE + "(" + COL_MAIL + "));";

    private static final String CREATE_TABLE_USER_OFFLINE = "CREATE TABLE " + TABLE_USER_OFFLINE + " (" +
            COL_MAIL + " VARCHAR(255) PRIMARY KEY, " +
            COL_NOM + " VARCHAR(255), " +
            COL_PRENOM + " VARCHAR(255), " +
            COL_NUM_TEL + " VARCHAR(15), " +
            COL_ADRESSE_PHOTO + " VARCHAR(255) UNIQUE, " +
            COL_STATUT + " VARCHAR(8) NOT NULL);";

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESEAU);
        db.execSQL(CREATE_TABLE_COMPTE_RESEAU);
        db.execSQL(CREATE_TABLE_USER_OFFLINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTE_RESEAU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESEAU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_OFFLINE);
        onCreate(db);
    }
}
