package com.example.greentechlives;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelperGreenTechLives extends SQLiteOpenHelper {

    //CREATE TABLE "TABLE_FOR_DATA"
    String table = "CREATE TABLE TABLE_FOR_DATA (_ID INTEGER PRIMARY KEY AUTOINCREMENT, BATTERY_LEVEL INTEGER, CO2_PRODUCED FLOAT)";

    public OpenHelperGreenTechLives(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_FOR_DATA");
        db.execSQL(table);

    }

    //TO OPEN THE DATABASE
    public void _open() {
        this.getWritableDatabase();

    }

    //TO CLOSE THE DATABASE
    public void _close() {
        this.close();

    }

    //TO APPEND DATA
    public void appendData (Integer batLevel, Float co2_produced) {
        ContentValues _values = new ContentValues();
        _values.put("BATTERY_LEVEL", batLevel);
        _values.put("CO2_PRODUCED", co2_produced);
        this.getWritableDatabase().insert("TABLE_FOR_DATA", null, _values);

    }

    //APPEND DATA #2
    public void appenDataDos (Integer batLevel, Float co2_produced) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO TABLE_FOR_DATA("+batLevel+", "+co2_produced+")");
            db.close();
        }

    }


}
