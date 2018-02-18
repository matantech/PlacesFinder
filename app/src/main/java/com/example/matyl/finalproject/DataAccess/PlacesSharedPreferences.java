package com.example.matyl.finalproject.DataAccess;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by matyl on 24/01/2018.
 */

public class PlacesSharedPreferences {

    Gson gson;
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    ArrayList<PlaceModel> places = new ArrayList<>();

    private static final String PLACES_KEY="favourite places";
    private static final String LOCATION_STORAGE="shared preferences";
    private static final String LANGUAGE_KEY = "language key";
    private static final String SPINNER_KEY = "spinner key";

    public PlacesSharedPreferences(Context context) {
        setting=context.getSharedPreferences(LOCATION_STORAGE,context.MODE_PRIVATE);
        editor =setting.edit();
        gson = new Gson();
    }

    public void savePlaces(ArrayList<PlaceModel> favouritesPlaces)
    {
            String json = gson.toJson(favouritesPlaces);
            editor.putString(PLACES_KEY, json);
            editor.apply();

    }

    public ArrayList<PlaceModel> loadPlaces() {
        String json = setting.getString(PLACES_KEY, "");
        if (json.equals(""))
        {
            return null;
        }
        Type type = new TypeToken<ArrayList<PlaceModel>>() {
        }.getType();
        ArrayList<PlaceModel> favouritePlaces = gson.fromJson(json, type);
        if (!favouritePlaces.isEmpty()) {
            for (PlaceModel place : favouritePlaces) {
                place.setImgRes(R.mipmap.favourits_icon);
            }
        }

        return favouritePlaces;
    }

    public void insertOrDelete(PlaceModel place)
    {
        places = loadPlaces();
        if (places != null) {
            if (places.contains(place))
            {
                places.remove(place);
            }
            else
            {
                places.add(place);
            }
            savePlaces(places);
        }else
        {
            create(place);
        }
    }

    public void create(PlaceModel place){
        ArrayList<PlaceModel> places = new ArrayList<>();

            places.add(place);


        savePlaces(places);

    }

    public void removeAll()
    {
        editor.clear();
        editor.commit();
    }

    public void saveLanguage(String language)
    {
        editor.putString(LANGUAGE_KEY, language);
        editor.apply();

    }

    public String loadLanguage()
    {
        String language = setting.getString(LANGUAGE_KEY,"en");
        return language;
    }

    public void savePosition(int position)
    {
        editor.putInt(SPINNER_KEY,position);
        editor.apply();
    }

    public int loadPosition()
    {
        int position = setting.getInt(SPINNER_KEY,0);
        return position;
    }

}
