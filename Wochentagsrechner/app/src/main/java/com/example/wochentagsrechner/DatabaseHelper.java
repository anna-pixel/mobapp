package com.example.wochentagsrechner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    ArrayAdapter adapter;

    public static final String DATABASE_NAME = "Wochentage.db";
    public static final String TABLE_NAME = "wochentage_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATUM";
    public static final String COL_3 = "EINGABE";
    public static final String COL_4 = "ERGEBNIS";
    public static final String COL_5 = "KOMMENTAR";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " INTEGER NOT NULL, " +
                COL_3 + " VARCHAR(10) NOT NULL," +
                COL_4 + " VARCHAR(10) NOT NULL," +
                COL_5 + " VARCHAR(45))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String input, String result, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, System.currentTimeMillis());
        contentValues.put(COL_3, input);
        contentValues.put(COL_4, result);
        contentValues.put(COL_5, comment);
        long check = db.insert(TABLE_NAME, null, contentValues);

        if (check == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayAdapter<DBList> getAllData(Context context){
        ArrayList<DBList> list = new ArrayList<DBList>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        res.moveToFirst();
        while (res.moveToNext()){
            DBList l = new DBList();
            l.setId(res.getInt(res.getColumnIndex(COL_1)));
            l.setDatum(res.getInt(res.getColumnIndex(COL_2)));
            l.setEingabe(res.getString(res.getColumnIndex(COL_3)));
            l.setErgebnis(res.getString(res.getColumnIndex(COL_4)));
            l.setKommentar(res.getString(res.getColumnIndex(COL_5)));
            list.add(l);
        }
        ArrayAdapter<DBList> arrayAdapter = new ArrayAdapter<DBList>(context,android.R.layout.simple_expandable_list_item_1, list);
        return arrayAdapter;
    }
}
