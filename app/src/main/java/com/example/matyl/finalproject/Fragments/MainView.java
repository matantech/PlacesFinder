package com.example.matyl.finalproject.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.matyl.finalproject.Adapters.PlacesAdapter;
import com.example.matyl.finalproject.DataAccess.DB.DAL;
import com.example.matyl.finalproject.DataAccess.PlacesSharedPreferences;
import com.example.matyl.finalproject.EndPoints.FindPlaceService;
import com.example.matyl.finalproject.Models.GooglePlaceModel;
import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainView extends Fragment {

    EditText userInput;
    ImageView btnSearch;
    RecyclerView recyclerView;
    ArrayList<PlaceModel> places = new ArrayList<>();
    PlacesAdapter placesAdapter;
    PlacesSharedPreferences placesSharedPreferences;
    FloatingActionButton btnMap;
    final int mainContainer = R.id.fragmentContainer;
    DAL dal;
    String language;



    public MainView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        dal = new DAL(getContext());
        placesSharedPreferences = new PlacesSharedPreferences(getContext());

        userInput = (EditText)v.findViewById(R.id.userInputMainActivity);
        btnSearch = (ImageView)v.findViewById(R.id.btnSearchMainActivity);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        btnMap = (FloatingActionButton)v.findViewById(R.id.floatingActionButtonMain);
        language = "";
        MyListner ms1  = new MyListner();
        btnSearch.setOnClickListener(ms1);
        btnMap.setOnClickListener(ms1);


        //places = getPlaces();
        //dal.savePlaces(places);
        placesAdapter = new PlacesAdapter(places,getContext());
        recyclerView.setAdapter(placesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    public class MyListner implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btnSearchMainActivity:
                    String text = userInput.getText().toString();
                    text.toLowerCase();
                    searchCity(text);
                    break;
                case R.id.floatingActionButtonMain:
                    MapView map = new MapView();
                    Bundle bundle = new Bundle();
                    PlaceModel placeToMap = places.get(0);
                    bundle.putSerializable("placeToMap", placeToMap);
                    map.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(mainContainer,map)
                            .addToBackStack(null)
                            .commit();


            }


        }
    }

    private void searchCity(String searchText)
    {
        places.clear();
        language = placesSharedPreferences.loadLanguage();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FindPlaceService service = retrofit.create(FindPlaceService.class);


        final Call<GooglePlaceModel> promise = service.getPlaceByQuery(searchText,language,FindPlaceService.API_KEY);
        promise.enqueue(new Callback<GooglePlaceModel>() {
            @Override
            public void onResponse(Call<GooglePlaceModel> call, Response<GooglePlaceModel> response) {
                GooglePlaceModel googlePlaceModel = response.body();
                GooglePlaceModel.Results results = googlePlaceModel.getResults().get(0);
                float lat = results.geometry.location.lat;
                float lng = results.geometry.location.lng;
                PlaceModel place = new PlaceModel(results.cityName.toString(), R.mipmap.place_navigation_icon, 123, lat, lng);
                places.add(place);
                placesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GooglePlaceModel> call, Throwable t) {

            }
        });




        /*for(PlaceModel place :getPlaces())
        {
            if (place.getName().toLowerCase().contains(searchText) || searchText.equals("")) {
                places.add(place);
            }


        }*/




    }

    public void changeToEnglish()
    {
        language = "en";

    }

    public void changeToHebrew()
    {
        language = "iw";
    }


   /* private ArrayList<PlaceModel> getPlaces()
    {
        ArrayList<PlaceModel> placesNew = new ArrayList<>();
        placesNew.add(new PlaceModel("haifa",R.mipmap.place_navigation_icon,123,123,123));
        placesNew.add(new PlaceModel("Tel-Aviv",R.mipmap.place_navigation_icon,123,123,123));
        placesNew.add(new PlaceModel("Jerusalem",R.mipmap.place_navigation_icon,123,123,123));
        placesNew.add(new PlaceModel("Eilat",R.mipmap.place_navigation_icon,123,123,123));
        return placesNew;

    }*/


}
