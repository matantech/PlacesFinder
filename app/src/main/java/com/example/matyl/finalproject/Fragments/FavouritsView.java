package com.example.matyl.finalproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.matyl.finalproject.Adapters.FavouritesPlacesAdapter;
import com.example.matyl.finalproject.DataAccess.DB.DAL;
import com.example.matyl.finalproject.DataAccess.PlacesSharedPreferences;
import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritsView extends Fragment {

    RecyclerView favouritsRecyclerView;
    ArrayList<PlaceModel> favouritsPlaces = new ArrayList<PlaceModel>();
    FavouritesPlacesAdapter favouritsPlacesAdapter;
    PlacesSharedPreferences placesSharedPreferences;
    DAL dal;


    public FavouritsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_favourits, container, false);
        dal = new DAL(getContext());

        favouritsRecyclerView = (RecyclerView)v.findViewById(R.id.favouritsActivityRecyclerView);
        placesSharedPreferences = new PlacesSharedPreferences(getContext());

        favouritsPlaces = placesSharedPreferences.loadPlaces();
        if (favouritsPlaces != null)
        {
            loadRecyclerView();
        }else 
        {
            Toast.makeText(getContext(), "No Favourits!", Toast.LENGTH_SHORT).show();
        }

        return v;

    }



    public void loadRecyclerView()
    {
        favouritsPlacesAdapter = new FavouritesPlacesAdapter(favouritsPlaces,getContext());
        favouritsRecyclerView.setAdapter(favouritsPlacesAdapter);
        favouritsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}


