package com.baez.places;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailsPlaceModel {
    public class Root implements Serializable {

        @SerializedName("result")
        public Result result;
        @SerializedName("status")
        public String status;
    }

    public class Result implements Serializable {


        @SerializedName("formatted_phone_number")
        public String number;
        @SerializedName("name")
        public String name;
        @SerializedName("price_level")
        public int price_level;
        @SerializedName("rating")
        public float rating;
        @SerializedName("vicinity")
        public String vicinity;
        @SerializedName("website")
        public String website;


    }
}
