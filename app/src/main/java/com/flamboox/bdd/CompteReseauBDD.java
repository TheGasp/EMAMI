package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class CompteReseauBDD {
    private static final int VERSION_BDD = 2;
    private static final String NOM_BDD = "emami.db";

    private static final String TABLE_COMPTE_RESEAU = "COMPTE_RESEAU";
    private static final String COL_ID_COMPTE = "idCompte";
    private static final int NUM_COL_ID_COMPTE = 0;
    private static final String COL_URL_RESEAU = "urlReseau";
    private static final int NUM_COL_URL_RESEAU = 1;
    private static final String COL_MAIL_USER = "mailUser";
    private static final int NUM_COL_MAIL_USER = 2;

    private SQLiteDatabase bdd;
    private AppDatabase maBaseSQLite;

    public CompteReseauBDD(Context context) {
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

    public long insertCompteReseau(CompteReseau compteReseau) {
        if (compteReseau == null || compteReseau.getIdCompte() == null || compteReseau.getMailUser() == null) {
            throw new IllegalArgumentException("CompteReseau, ID Compte, and Mail User cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_ID_COMPTE, compteReseau.getIdCompte());
        values.put(COL_URL_RESEAU, compteReseau.getUrlReseau());
        values.put(COL_MAIL_USER, compteReseau.getMailUser());

        try {
            return bdd.insertOrThrow(TABLE_COMPTE_RESEAU, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateCompteReseau(String idCompte, CompteReseau compteReseau) {
        if (idCompte == null || compteReseau == null || compteReseau.getMailUser() == null) {
            throw new IllegalArgumentException("ID Compte, CompteReseau, and Mail User cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COL_URL_RESEAU, compteReseau.getUrlReseau());
        values.put(COL_MAIL_USER, compteReseau.getMailUser());

        try {
            return bdd.update(TABLE_COMPTE_RESEAU, values, COL_ID_COMPTE + " = ?", new String[]{idCompte});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int removeCompteReseauWithIdCompte(String idCompte) {
        if (idCompte == null) {
            throw new IllegalArgumentException("ID Compte cannot be null");
        }

        try {
            return bdd.delete(TABLE_COMPTE_RESEAU, COL_ID_COMPTE + " = ?", new String[]{idCompte});
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public CompteReseau getCompteReseauWithIdCompte(String idCompte) {
        if (idCompte == null) {
            throw new IllegalArgumentException("ID Compte cannot be null");
        }

        Cursor c = null;
        try {
            c = bdd.query(TABLE_COMPTE_RESEAU, new String[]{COL_ID_COMPTE, COL_URL_RESEAU, COL_MAIL_USER}, COL_ID_COMPTE + " LIKE ?", new String[]{idCompte}, null, null, null);
            return cursorToCompteReseau(c);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private CompteReseau cursorToCompteReseau(Cursor c) {
        if (c == null || c.getCount() == 0) {
            return null;
        }

        c.moveToFirst();
        CompteReseau compteReseau = new CompteReseau(c.getString(NUM_COL_ID_COMPTE), c.getString(NUM_COL_URL_RESEAU), c.getString(NUM_COL_MAIL_USER));
        return compteReseau;
    }
}
