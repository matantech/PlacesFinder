package com.example.matyl.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.matyl.finalproject.Adapters.FavouritesPlacesAdapter;
import com.example.matyl.finalproject.Adapters.PlacesAdapter;
import com.example.matyl.finalproject.DataAccess.PlacesSharedPreferences;
import com.example.matyl.finalproject.Models.PlaceModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Favourits extends AppCompatActivity {

    RecyclerView favouritsRecyclerView;
    ArrayList<PlaceModel> favouritsPlaces = new ArrayList<PlaceModel>();
    FavouritesPlacesAdapter favouritsPlacesAdapter;
    SwipeRefreshLayout swipeRefresh;
    PlacesSharedPreferences placesSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourits);
        favouritsRecyclerView = (RecyclerView)findViewById(R.id.favouritsActivityRecyclerView);

        placesSharedPreferences.loadPlaces();
        if (!favouritsPlaces.isEmpty())
        {
            loadRecyclerView();
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favouritsPlacesAdapter.notifyDataSetChanged();
                swipeRefresh.stopNestedScroll();

            }
        });

    }
    
    
    
    public void loadRecyclerView()
    {
        favouritsPlacesAdapter = new FavouritesPlacesAdapter(favouritsPlaces,this);
        favouritsRecyclerView.setAdapter(favouritsPlacesAdapter);
        favouritsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
