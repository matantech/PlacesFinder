package com.example.matyl.finalproject.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.matyl.finalproject.Adapters.FavouritesPlacesAdapter;
import com.example.matyl.finalproject.DataAccess.PlacesSharedPreferences;
import com.example.matyl.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsView extends Fragment {

    Button btnRemoveAll;
    Switch switchMIles;
    Spinner spinnerLanguages;
    FavouritesPlacesAdapter favouritesPlacesAdapter = new FavouritesPlacesAdapter();
    PlacesSharedPreferences placesSharedPreferences;
    String language;

    public SettingsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings_view, container, false);
        placesSharedPreferences = new PlacesSharedPreferences(getContext());

        btnRemoveAll = (Button)v.findViewById(R.id.removeFavouritsButton);
        switchMIles = (Switch)v.findViewById(R.id.switchMiles);
        spinnerLanguages = (Spinner)v.findViewById(R.id.spinnerLanguages);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);
        spinnerLanguages.setSelection(placesSharedPreferences.loadPosition());

        SpinnerActivity spinnerActivity = new SpinnerActivity();
        spinnerLanguages.setOnItemSelectedListener(spinnerActivity);

        MyListner ms1 = new MyListner();
        btnRemoveAll.setOnClickListener(ms1);


        return v;
    }

    class MyListner implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            favouritesPlacesAdapter.removeAllFavourits();
                            placesSharedPreferences.removeAll();
                            favouritesPlacesAdapter.notifyDataSetChanged();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.dismiss();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Remove All Favourites Places?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();


        }
    }

    class SpinnerActivity implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getSelectedItemPosition())
            {
                case 0:
                    language = "en";
                    placesSharedPreferences.saveLanguage(language);
                    placesSharedPreferences.savePosition(parent.getSelectedItemPosition());
                    break;
                case 1:
                    language = "iw";
                    placesSharedPreferences.saveLanguage(language);
                    placesSharedPreferences.savePosition(parent.getSelectedItemPosition());
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }



}
