package com.example.fares.port_finder.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "portfinder.db";
    public static  final String TABLE_NAME ="utilisateur_table";
    public static  final String COL_1 ="ID";
    public static  final String COL_2="USERNAME";
    public static  final String COL_3 ="PASSWORD";
    public static  final String COL_4 ="NOM";
    public static  final String COL_5 ="PRENOM";
    public static  final String COL_6 ="ADRESSE";
    public static  final String COL_7 ="DATENAISS";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT," +
                "PASSWORD TEXT,NOM TEXT,PRENOM TEXT,ADRESSE TEXT,DATENAISS DATE)");


    }
    public boolean insertData(String username,String password,String nom,String prenom,String adresse,String datenaiss) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,nom);
        contentValues.put(COL_5,prenom);
        contentValues.put(COL_6,adresse);
        contentValues.put(COL_7,datenaiss);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor c = db.rawQuery( "SELECT * FROM utilisateur_table t WHERE t.username = '"+ username + "' AND t.password = '" + password+"'", null);

        if(c.getCount() <= 0) {
            c.close();
            db.close();
            return false;
        } else {
            c.close();
            db.close();
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
