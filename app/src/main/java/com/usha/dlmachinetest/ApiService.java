package com.usha.dlmachinetest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "https://api.foursquare.com/v2/";
    @GET("venues/search")
    Call<MainModel> getVenuesList(@Query("ll") String latLong, @Query("oauth_token")
            String token, @Query("v") String date);

}
