package com.example.matyl.finalproject.DataAccess.DB;

/**
 * Created by matyl on 29/01/2018.
 */

public class PlaceEntry {

    static final String TABLE_NAME = "places";
    static final String ID = "id";
    static final String NAME = "name";
    static final String LAT = "lat";
    static final String LNG = "lng";
    static final String TABLE_CREATE =
            " CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    LAT + " REAL, " +
                    LNG + " REAL " +
                    " )";
}
