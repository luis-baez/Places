package com.baez.places;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPlace extends AppCompatActivity {

    String placeID;
    ApiInterface apiInterface;
    DetailsPlaceModel.Result results;

    TextView txvName, txvVicinity, txvPhoneNumber, txvWebsite, txvRating, txvPriceLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_place);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        placeID = getIntent().getStringExtra("placeID");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        txvName = findViewById(R.id.txvName);
        txvVicinity = findViewById(R.id.txvVicinity);
        txvPhoneNumber = findViewById(R.id.txvPhoneNumber);
        txvWebsite = findViewById(R.id.txvWebsite);
        txvRating = findViewById(R.id.txvRating);
        txvPriceLevel = findViewById(R.id.txvPriceLevel);
        fetchPlace(placeID);
    }


    private void fetchPlace(final String placeID) {

        Call<DetailsPlaceModel.Root> call = apiInterface.doPlace(placeID,"name,rating,formatted_phone_number,price_level,website,vicinity", ApiClient.GOOGLE_PLACE_API_KEY);
        call.enqueue(new Callback<DetailsPlaceModel.Root>() {
            @Override
            public void onResponse(Call<DetailsPlaceModel.Root> call, Response<DetailsPlaceModel.Root> response) {
                DetailsPlaceModel.Root root = response.body();


                if (response.isSuccessful()) {

                    if (root.status.equals("OK")) {

                        results = root.result;

                        //RecyclerViewAdapter adapterStores = new RecyclerViewAdapter(results, PlacesActivity.this);
                        //recyclerView.setAdapter(adapterStores);

                        txvName.setText(results.name);
                        txvVicinity.setText(results.vicinity);
                        txvPhoneNumber.setText(results.number);
                        txvWebsite.setText(results.website);
                        txvRating.setText(String.valueOf(results.rating));

                        switch (results.price_level){
                            case 0:
                                txvPriceLevel.setText("Gratis");
                                break;
                            case 1:
                                txvPriceLevel.setText("Barato");
                                break;
                            case 2:
                                txvPriceLevel.setText("Moderado");
                                break;
                            case 3:
                                txvPriceLevel.setText("Costoso");
                                break;
                            case 4:
                                txvPriceLevel.setText("Muy caro");
                                break;
                            default:
                                txvPriceLevel.setText("No se tiene información");
                                break;
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "No se encuentra "+placeID, Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() != 200) {
                    Toast.makeText(getApplicationContext(), "Error " + response.code(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<DetailsPlaceModel.Root> call, Throwable t) {
                call.cancel();
            }
        });


    }


}


