package com.example.matyl.finalproject.DataAccess.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matyl on 29/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "locationDB";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(PlaceEntry.TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ PlaceEntry.TABLE_NAME);
        onCreate(db);
    }
}
