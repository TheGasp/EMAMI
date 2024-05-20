package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompteReseauBDD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CompteReseauDB.db";
    private static final String TABLE_NAME = "CompteReseau";
    private static final String COLUMN_ID_USER = "idUser";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_URL_RESEAU = "urlReseau";
    private static final String TYPE_INTEGER = "INTEGER";
    private static final String TYPE_TEXT = "TEXT";

    public CompteReseauBDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID_USER + " " + TYPE_INTEGER + ", "
                + COLUMN_URL + " " + TYPE_TEXT + " PRIMARY KEY, "
                + COLUMN_URL_RESEAU + " " + TYPE_INTEGER + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCompteReseau(CompteReseau compteReseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_USER, compteReseau.getIdUser());
            values.put(COLUMN_URL, compteReseau.getUrl());
            values.put(COLUMN_URL_RESEAU, compteReseau.getUrlReseau());

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public CompteReseau getCompteReseau(String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        CompteReseau compteReseau = null;

        try {
            cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID_USER, COLUMN_URL, COLUMN_URL_RESEAU},
                    COLUMN_URL + "=?", new String[]{url}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                compteReseau = new CompteReseau(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)
                );
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return compteReseau;
    }

    public int updateCompteReseau(CompteReseau compteReseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsUpdated;

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_USER, compteReseau.getIdUser());
            values.put(COLUMN_URL, compteReseau.getUrl());
            values.put(COLUMN_URL_RESEAU, compteReseau.getUrlReseau());

            rowsUpdated = db.update(TABLE_NAME, values, COLUMN_URL + " = ?",
                    new String[]{compteReseau.getUrl()});
        } finally {
            db.close();
        }
        return rowsUpdated;
    }

    public void deleteCompteReseau(CompteReseau compteReseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_URL + " = ?",
                    new String[]{compteReseau.getUrl()});
        } finally {
            db.close();
        }
    }
}
