package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class ReseauBDD {
    private static final int VERSION_BDD = 2;
    private static final String NOM_BDD = "emami.db";

    private static final String TABLE_RESEAU = "RESEAU";
    private static final String COL_URL = "url";
    private static final int NUM_COL_URL = 0;
    private static final String COL_DENOMINATION = "denomination";
    private static final int NUM_COL_DENOMINATION = 1;
    private static final String COL_ADRESSE_LOGO = "adresseLogo";
    private static final int NUM_COL_ADRESSE_LOGO = 2;

    private SQLiteDatabase bdd;
    private AppDatabase maBaseSQLite;

    public ReseauBDD(Context context) {
        maBaseSQLite = new AppDatabase(context);
    }

    public void open() throws SQLException {
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertReseau(Reseau reseau) {
        if (reseau == null || reseau.getUrl() == null || reseau.getDenomination() == null) {
            throw new IllegalArgumentException("Reseau, URL, and denomination cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_URL, reseau.getUrl());
        values.put(COL_DENOMINATION, reseau.getDenomination());
        values.put(COL_ADRESSE_LOGO, reseau.getAdresseLogo());

        try {
            return bdd.insertOrThrow(TABLE_RESEAU, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateReseau(String url, Reseau reseau) {
        if (url == null || reseau == null || reseau.getDenomination() == null) {
            throw new IllegalArgumentException("URL, Reseau, and denomination cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_DENOMINATION, reseau.getDenomination());
        values.put(COL_ADRESSE_LOGO, reseau.getAdresseLogo());

        try {
            return bdd.update(TABLE_RESEAU, values, COL_URL + " = ?", new String[]{url});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int removeReseauWithURL(String url) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }

        try {
            return bdd.delete(TABLE_RESEAU, COL_URL + " = ?", new String[]{url});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Reseau getReseauWithURL(String url) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }

        Cursor c = null;
        try {
            c = bdd.query(TABLE_RESEAU, new String[]{COL_URL, COL_DENOMINATION, COL_ADRESSE_LOGO}, COL_URL + " LIKE ?", new String[]{url}, null, null, null);
            return cursorToReseau(c);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private Reseau cursorToReseau(Cursor c) {
        if (c == null || c.getCount() == 0) {
            return null;
        }

        c.moveToFirst();
        Reseau reseau = new Reseau(c.getString(NUM_COL_URL), c.getString(NUM_COL_DENOMINATION), c.getString(NUM_COL_ADRESSE_LOGO));
        return reseau;
    }
}
