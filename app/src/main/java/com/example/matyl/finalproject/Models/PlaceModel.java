package com.example.matyl.finalproject.Models;

import java.io.Serializable;

/**
 * Created by matyl on 15/01/2018.
 */

public class PlaceModel implements Serializable {

    private int id;
    private String name;
    private float lat;
    private float lng;
    private int imgRes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public PlaceModel()
    {

    }

    public PlaceModel(String name, int imgRes,int id, Float lat, Float lng) {
        this.name = name;
        this.imgRes = imgRes;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }


}
