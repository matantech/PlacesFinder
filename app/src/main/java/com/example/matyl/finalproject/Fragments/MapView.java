package com.example.matyl.finalproject.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapView extends Fragment implements OnMapReadyCallback {

    FloatingActionButton btnMain;
    private GoogleMap mMap;
    PlaceModel place = new PlaceModel();
    final int mainContainer = R.id.fragmentContainer;


    public MapView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        btnMain = (FloatingActionButton) v.findViewById(R.id.floatingActionButtonMap);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getArguments();
        place = (PlaceModel)bundle.getSerializable("placeToMap");

        MyListner ms1 = new MyListner();
        btnMain.setOnClickListener(ms1);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (place.getName().toString() != "")
        {
                float lat = place.getLat();
                float lng = place.getLng();
                String name = place.getName().toString();
                LatLng UCA = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(UCA).title(name + "")).showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, 17));

        }else 
        {
            Toast.makeText(getContext(), "No Places!", Toast.LENGTH_SHORT).show();
        }

    }


    public class MyListner implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            MainView main = new MainView();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(mainContainer,main)
                    .addToBackStack(null)
                    .commit();
        }
    }

}


