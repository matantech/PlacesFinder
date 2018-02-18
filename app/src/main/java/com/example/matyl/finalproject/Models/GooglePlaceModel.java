package com.example.matyl.finalproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matyl on 05/02/2018.
 */

public class GooglePlaceModel {

    @SerializedName("results")
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }


    public class Results
    {

        @SerializedName("name")
        public String cityName;

        @SerializedName("geometry")
        public Geometry geometry;

        public class Geometry
        {

            @SerializedName("location")
            public Location location;

            public class Location
            {

                @SerializedName("lat")
                public float lat;

                @SerializedName("lng")
                public float lng;
            }


        }


    }


}
