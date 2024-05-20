package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReseauBDD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ReseauDB.db";
    private static final String TABLE_NAME = "Reseau";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_DENOMINATION_RESEAU = "denomination_reseau";
    private static final String TYPE_TEXT = "TEXT";

    public ReseauBDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_URL + " " + TYPE_TEXT + " PRIMARY KEY, "
                + COLUMN_DENOMINATION_RESEAU + " " + TYPE_TEXT + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addReseau(Reseau reseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_URL, reseau.getUrl());
            values.put(COLUMN_DENOMINATION_RESEAU, reseau.getDenomination_reseau());

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public Reseau getReseau(String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        Reseau reseau = null;

        try {
            cursor = db.query(TABLE_NAME, new String[]{COLUMN_URL, COLUMN_DENOMINATION_RESEAU},
                    COLUMN_URL + "=?", new String[]{url}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                reseau = new Reseau(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return reseau;
    }

    public int updateReseau(Reseau reseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsUpdated;

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DENOMINATION_RESEAU, reseau.getDenomination_reseau());

            rowsUpdated = db.update(TABLE_NAME, values, COLUMN_URL + " = ?",
                    new String[]{reseau.getUrl()});
        } finally {
            db.close();
        }
        return rowsUpdated;
    }

    public void deleteReseau(Reseau reseau) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_URL + " = ?",
                    new String[]{reseau.getUrl()});
        } finally {
            db.close();
        }
    }
}
