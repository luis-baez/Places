package com.baez.places;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlacesModel {

    public class Root implements Serializable {

        @SerializedName("results")
        public List<Results> results = new ArrayList<>();
        @SerializedName("status")
        public String status;
    }

    public class Results implements Serializable {


        @SerializedName("geometry")
        public Geometry geometry;
        @SerializedName("vicinity")
        public String vicinity;
        @SerializedName("name")
        public String name;
        @SerializedName("place_id")
        public String place_id;

    }

    public class Geometry implements Serializable{

        @SerializedName("location")
        public Location location;

    }

    public class Location implements Serializable {

        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;


    }



}
