package com.example.matyl.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matyl.finalproject.DataAccess.PlacesSharedPreferences;
import com.example.matyl.finalproject.Favourits;
import com.example.matyl.finalproject.ItemClickListner;
import com.example.matyl.finalproject.Models.PlaceModel;
import com.example.matyl.finalproject.R;
import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by matyl on 15/01/2018.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {


    ArrayList<PlaceModel> placesModels;
    private Context context;
    PlacesSharedPreferences placesSharedPreferences;




    public PlacesAdapter(ArrayList<PlaceModel> placeModels, Context context) {
        this.placesModels = placeModels;
        this.context = context;
        placesSharedPreferences = new PlacesSharedPreferences(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_list_layout,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final PlaceModel place = placesModels.get(position);
        holder.initViews(place);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void OnClick(View view, int position, boolean isLongClick) {
                if (isLongClick)
                {
                    Toast.makeText(context, "You Long Click On "+placesModels.get(position).getName().toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PlaceModel favouritesPlace = placesModels.get(position);
                    placesSharedPreferences.insertOrDelete(favouritesPlace);
                    holder.makeStar(favouritesPlace);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return placesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView placeTV;
        ImageView imageView;
        private ItemClickListner itemClickListner;

        public ViewHolder(View itemView, TextView placeTV, ImageView imageView) {
            super(itemView);
            this.placeTV = itemView.findViewById(R.id.cityTv);
            this.imageView = itemView.findViewById(R.id.imageView);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.placeTV = itemView.findViewById(R.id.cityTv);
            this.imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private void makeStar(PlaceModel place)
        {
            int regular = R.mipmap.place_navigation_icon;
            int favouite = R.mipmap.favourits_icon;
            if (place.getImgRes()== regular)
            {
                imageView.setImageResource(favouite);
                place.setImgRes(favouite);
            }else
            {
                imageView.setImageResource(regular);
                place.setImgRes(regular);
            }
        }

        public void setItemClickListner(ItemClickListner itemClickListner)
        {
            this.itemClickListner = itemClickListner;
        }

        public void initViews(PlaceModel placeModel) {

            placeTV.setText(placeModel.getName());
            imageView.setImageResource(placeModel.getImgRes());


        }

        @Override
        public void onClick(View v) {
            itemClickListner.OnClick(v,getAdapterPosition(),false);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListner.OnClick(v,getAdapterPosition(),true);
            return true;
        }

    }

}
