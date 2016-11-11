package com.david.flashcardas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "flashcardDB.SQLite";
    public static final String FLASHCARD_TABLE = "flashcards";

    public static final String RECORD_ID = "ID";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String SIDE_A = "sideA";
    public static final String SIDE_B = "sideB";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase flashcardDB) {

        String sqlStatement = "CREATE TABLE " + FLASHCARD_TABLE
                + " (" + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COLUMN_SUBJECT + " TEXT,"
                + SIDE_A + " TEXT,"
                + SIDE_B + " TEXT"
                + ");";

        Log.d("Flashcard database", sqlStatement);
        flashcardDB.execSQL(sqlStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
