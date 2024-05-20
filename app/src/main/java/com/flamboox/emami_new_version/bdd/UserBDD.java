package com.flamboox.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserBDD {

    private static final String TABLE_USER = "user";
    private static final String COL_MAIL = "mail";
    private static final String COL_DENOMINATION = "denomination";
    private static final String COL_STATUT = "statut";

    private SQLiteDatabase bdd;
    private DatabaseHelper dbHelper;

    public UserBDD(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        bdd = dbHelper.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(User user){
        ContentValues values = new ContentValues();
        values.put(COL_MAIL, user.getMail());
        values.put(COL_DENOMINATION, user.getDenomination());
        values.put(COL_STATUT, user.getStatut());
        return bdd.insert(TABLE_USER, null, values);
    }

    public int updateUser(String mail, User user){
        ContentValues values = new ContentValues();
        values.put(COL_DENOMINATION, user.getDenomination());
        values.put(COL_STATUT, user.getStatut());
        return bdd.update(TABLE_USER, values, COL_MAIL + " = ?", new String[]{mail});
    }

    public int removeUserWithMail(String mail){
        return bdd.delete(TABLE_USER, COL_MAIL + " = ?", new String[]{mail});
    }

    public User getUserWithMail(String mail){
        Cursor c = bdd.query(TABLE_USER, new String[] {COL_MAIL, COL_DENOMINATION, COL_STATUT}, COL_MAIL + " = ?", new String[]{mail}, null, null, null);
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        User user = new User(c.getString(c.getColumnIndex(COL_MAIL)), c.getString(c.getColumnIndex(COL_DENOMINATION)));
        user.setStatut(c.getString(c.getColumnIndex(COL_STATUT)));
        c.close();

        return user;
    }
}
