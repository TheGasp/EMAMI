package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class UserBDD {
    private static final int VERSION_BDD = 2;
    private static final String NOM_BDD = "emami.db";

    private static final String TABLE_USER_OFFLINE = "USER_OFFLINE";
    private static final String COL_MAIL = "mail";
    private static final int NUM_COL_MAIL = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_PRENOM = "prenom";
    private static final int NUM_COL_PRENOM = 2;
    private static final String COL_NUM_TEL = "numTel";
    private static final int NUM_COL_NUM_TEL = 3;
    private static final String COL_ADRESSE_PHOTO = "adressePhoto";
    private static final int NUM_COL_ADRESSE_PHOTO = 4;
    private static final String COL_STATUT = "statut";
    private static final int NUM_COL_STATUT = 5;

    private SQLiteDatabase bdd;
    private AppDatabase maBaseSQLite;

    public UserBDD(Context context) {
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

    public long insertUser(User user) {
        if (user == null || user.getMail() == null || user.getStatut() == null) {
            throw new IllegalArgumentException("User, Mail, and Statut cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_MAIL, user.getMail());
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_NUM_TEL, user.getNumTel());
        values.put(COL_ADRESSE_PHOTO, user.getAdressePhoto());
        values.put(COL_STATUT, user.getStatut());

        try {
            return bdd.insertOrThrow(TABLE_USER_OFFLINE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateUser(String mail, User user) {
        if (mail == null || user == null || user.getStatut() == null) {
            throw new IllegalArgumentException("Mail, User, and Statut cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_NUM_TEL, user.getNumTel());
        values.put(COL_ADRESSE_PHOTO, user.getAdressePhoto());
        values.put(COL_STATUT, user.getStatut());

        try {
            return bdd.update(TABLE_USER_OFFLINE, values, COL_MAIL + " = ?", new String[]{mail});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int removeUserWithMail(String mail) {
        if (mail == null) {
            throw new IllegalArgumentException("Mail cannot be null");
        }

        try {
            return bdd.delete(TABLE_USER_OFFLINE, COL_MAIL + " = ?", new String[]{mail});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public User getUserWithMail(String mail) {
        if (mail == null) {
            throw new IllegalArgumentException("Mail cannot be null");
        }

        Cursor c = null;
        try {
            c = bdd.query(TABLE_USER_OFFLINE, new String[]{COL_MAIL, COL_NOM, COL_PRENOM, COL_NUM_TEL, COL_ADRESSE_PHOTO, COL_STATUT}, COL_MAIL + " LIKE ?", new String[]{mail}, null, null, null);
            return cursorToUser(c);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private User cursorToUser(Cursor c) {
        if (c == null || c.getCount() == 0) {
            return null;
        }

        c.moveToFirst();
        User user = new User(c.getString(NUM_COL_MAIL), c.getString(NUM_COL_NOM), c.getString(NUM_COL_PRENOM), c.getString(NUM_COL_NUM_TEL), c.getString(NUM_COL_ADRESSE_PHOTO), c.getString(NUM_COL_STATUT));
        return user;
    }
}
