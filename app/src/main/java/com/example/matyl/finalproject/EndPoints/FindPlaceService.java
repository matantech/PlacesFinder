package com.example.matyl.finalproject.EndPoints;

import com.example.matyl.finalproject.Models.GooglePlaceModel;
import com.example.matyl.finalproject.Models.PlaceModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by matyl on 05/02/2018.
 */

public interface FindPlaceService {

   String API_KEY = "AIzaSyD6c2kboyoJPltS9Q37cwg7audDIb5-2ms";


   @GET("https://maps.googleapis.com/maps/api/place/textsearch/json?")
   Call<GooglePlaceModel> getPlaceByQuery(@Query("query") String cityName,@Query("language") String language, @Query("key") String key);





}
