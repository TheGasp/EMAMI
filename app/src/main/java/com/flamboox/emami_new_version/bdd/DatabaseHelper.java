package com.flamboox.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app_database.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_COMPTE_RESEAU = "compte_reseau";
    private static final String TABLE_REQUETE = "requete";
    private static final String TABLE_RESEAU = "reseau";
    private static final String TABLE_USER = "user";

    // CompteReseau table columns
    private static final String COLUMN_COMPTE_RESEAU_USER_ID = "user_id";
    private static final String COLUMN_COMPTE_RESEAU_URL = "url";
    private static final String COLUMN_COMPTE_RESEAU_URL_RESEAU = "url_reseau";

    // Requete table columns
    private static final String COLUMN_REQUETE_MAIL = "mail";

    // Reseau table columns
    private static final String COLUMN_RESEAU_URL = "url";
    private static final String COLUMN_RESEAU_DENOMINATION_RESEAU = "denomination";

    // User table columns
    private static final String COLUMN_USER_MAIL = "mail";
    private static final String COLUMN_USER_DENOMINATION = "denomination";
    private static final String COLUMN_USER_STATUT = "statut";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create CompteReseau table with composite primary key
        String CREATE_TABLE_COMPTE_RESEAU = "CREATE TABLE " + TABLE_COMPTE_RESEAU + "("
                + COLUMN_COMPTE_RESEAU_USER_ID + " INTEGER,"
                + COLUMN_COMPTE_RESEAU_URL_RESEAU + " TEXT,"
                + COLUMN_COMPTE_RESEAU_URL + " TEXT,"
                + "PRIMARY KEY(" + COLUMN_COMPTE_RESEAU_USER_ID + ", " + COLUMN_COMPTE_RESEAU_URL_RESEAU + "))";
        db.execSQL(CREATE_TABLE_COMPTE_RESEAU);

        // Create Requete table with primary key
        String CREATE_TABLE_REQUETE = "CREATE TABLE " + TABLE_REQUETE + "("
                + COLUMN_REQUETE_MAIL + " TEXT PRIMARY KEY)";
        db.execSQL(CREATE_TABLE_REQUETE);

        // Create Reseau table with primary key
        String CREATE_TABLE_RESEAU = "CREATE TABLE " + TABLE_RESEAU + "("
                + COLUMN_RESEAU_URL + " TEXT PRIMARY KEY,"
                + COLUMN_RESEAU_DENOMINATION_RESEAU + " TEXT)";
        db.execSQL(CREATE_TABLE_RESEAU);

        // Create User table with primary key
        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_MAIL + " TEXT PRIMARY KEY,"
                + COLUMN_USER_DENOMINATION + " TEXT,"
                + COLUMN_USER_STATUT + " TEXT)";
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTE_RESEAU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUETE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESEAU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
