package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RequeteBDD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "app_database.db";
    private static final String TABLE_NAME = "Requete";
    private static final String COLUMN_MAIL_USER = "mailUser";
    private static final String TYPE_TEXT = "TEXT";

    public RequeteBDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_MAIL_USER + " " + TYPE_TEXT + " PRIMARY KEY)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRequete(RequeteEnCours requete) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MAIL_USER, requete.getMailUser());

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public RequeteEnCours getRequete(String mailUser) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        RequeteEnCours requete = null;

        try {
            cursor = db.query(TABLE_NAME, new String[]{COLUMN_MAIL_USER},
                    COLUMN_MAIL_USER + "=?", new String[]{mailUser}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                User user = getUserByEmail(cursor.getString(0));
                requete = new RequeteEnCours(user);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return requete;
    }

    public int updateRequete(RequeteEnCours requete) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsUpdated;

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MAIL_USER, requete.getMailUser());

            rowsUpdated = db.update(TABLE_NAME, values, COLUMN_MAIL_USER + " = ?",
                    new String[]{requete.getMailUser()});
        } finally {
            db.close();
        }
        return rowsUpdated;
    }

    public void deleteRequete(RequeteEnCours requete) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_MAIL_USER + " = ?",
                    new String[]{requete.getMailUser()});
        } finally {
            db.close();
        }
    }

    private User getUserByEmail(String mail) {
        return userBDD.getUserWithMail(mail);
    }
}
