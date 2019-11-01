package com.example.wochentagsrechner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Wochentage.db";
    public static final String TABLE_NAME = "wochentage_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATUM";
    public static final String COL_3 = "EINGABE";
    public static final String COL_4 = "ERGEBNIS";
    public static final String COL_5 = "KOMMENTAR";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " DATETIME NOT NULL, " +
                COL_3 + " VARCHAR(10) NOT NULL," +
                COL_4 + " VARCHAR(10) NOT NULL," +
                COL_5 + " VARCHAR(45))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
