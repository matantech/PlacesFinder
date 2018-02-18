package com.example.matyl.finalproject.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matyl.finalproject.Favourits;
import com.example.matyl.finalproject.ItemClickListner;
import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by matyl on 18/01/2018.
 */

public class FavouritesPlacesAdapter extends RecyclerView.Adapter<FavouritesPlacesAdapter.ViewHolder> {

    ArrayList<PlaceModel> favouritesPlaces = new ArrayList<>();
    private Context context;

    public FavouritesPlacesAdapter()
    {

    }

    public FavouritesPlacesAdapter(ArrayList<PlaceModel> favouritesPlaces, Context context) {
        this.favouritesPlaces = favouritesPlaces;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_list_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    private void removeNew(int position) {
        favouritesPlaces.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final PlaceModel place = favouritesPlaces.get(position);
        holder.initViews(place);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void OnClick(View view, final int position, boolean isLongClick) {
                if (isLongClick) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    removeNew(position);
                                    savePlaces(favouritesPlaces);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Delete Favourite Place?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                } else {

                    Toast.makeText(context, "You Short Click On " + favouritesPlaces.get(position).getName().toString(), Toast.LENGTH_SHORT).show();


                }


            }
        });



    }

    public void removeAllFavourits()
    {

        favouritesPlaces.removeAll(favouritesPlaces);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favouritesPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView placeTV;
        ImageView imageView;
        private ItemClickListner itemClickListner;


        public ViewHolder(View itemView) {
            super(itemView);
            this.placeTV = itemView.findViewById(R.id.cityTv);
            this.imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListner(ItemClickListner itemClickListner) {
            this.itemClickListner = itemClickListner;
        }

        public void initViews(PlaceModel placeModel) {
            placeTV.setText(placeModel.getName());
            imageView.setImageResource(placeModel.getImgRes());
        }

        @Override
        public void onClick(View v) {
            itemClickListner.OnClick(v, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListner.OnClick(v, getAdapterPosition(), true);
            return true;
        }
    }

    private void savePlaces(ArrayList<PlaceModel> favouritesPlaces) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favouritesPlaces);
        editor.putString("favourite places", json);
        editor.apply();
    }


}
