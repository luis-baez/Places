package com.baez.places;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("place/nearbysearch/json?")
    Call<PlacesModel.Root> doPlaces(@Query("type") String type, @Query("location") String location,@Query("radius") String radius, @Query("key") String key);

    @GET("place/details/json?")
    Call<DetailsPlaceModel.Root> doPlace(@Query("place_id") String placeId, @Query("fields") String fields, @Query("key") String key);

   }
