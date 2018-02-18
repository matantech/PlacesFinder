package com.example.matyl.finalproject.DataAccess.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.matyl.finalproject.Models.PlaceModel;

import java.util.ArrayList;

/**
 * Created by matyl on 29/01/2018.
 */

public class DAL {

    DBHelper helper;
    Context context;

    public DAL(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public void savePlace(PlaceModel place) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlaceEntry.NAME, place.getName());
        contentValues.put(PlaceEntry.LAT, place.getLat());
        contentValues.put(PlaceEntry.LNG, place.getLng());
        db.insert(PlaceEntry.TABLE_NAME, null, contentValues);
    }

    public void savePlaces(ArrayList<PlaceModel> places) {
        for (PlaceModel place : places) {
            savePlace(place);
        }
    }

    public ArrayList<PlaceModel> getAllPlaces() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String table = PlaceEntry.TABLE_NAME;
        String[] columns = null;
        String selection = null;
        String[] args = null;
        String groupBy = null;
        String having = null;
        String orderBy = PlaceEntry.ID + " DESC";
        String limit = null;
        ArrayList<PlaceModel> places = new ArrayList<>();

        Cursor cursor = db.query(table, columns, selection, args, groupBy, having, orderBy);
        if (cursor.moveToFirst()) {
            do
            {
                PlaceModel place = new PlaceModel();
                place.setId(cursor.getInt(cursor.getColumnIndex(PlaceEntry.ID)));
                place.setName(cursor.getString(cursor.getColumnIndex(PlaceEntry.NAME)));
                place.setLat(cursor.getFloat(cursor.getColumnIndex(PlaceEntry.LAT)));
                place.setLng(cursor.getFloat(cursor.getColumnIndex(PlaceEntry.LNG)));
                places.add(place);
            }
            while (cursor.moveToNext());
            cursor.close();
            return places;
        }


        return null;
    }

    public PlaceModel getPlace() {


        return null;
    }

}
