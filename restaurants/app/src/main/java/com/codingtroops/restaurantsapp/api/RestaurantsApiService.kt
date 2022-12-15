package com.codingtroops.restaurantsapp.api

import com.codingtroops.restaurantsapp.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsApiService {
    @GET("restaurants.json")
    fun getRestaurants() : Call<List<Restaurant>>
}