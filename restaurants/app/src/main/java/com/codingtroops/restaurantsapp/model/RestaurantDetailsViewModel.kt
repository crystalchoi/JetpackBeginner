package com.codingtroops.restaurantsapp.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.api.RestaurantsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.codingtroops.restaurantsapp.model.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantDetailsViewModel(
    private  val stateHandle: SavedStateHandle
) : ViewModel() {
    private var restInterface: RestaurantsApiService
    val state = mutableStateOf<Restaurant?>(null)

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        restInterface = retrofit.create(RestaurantsApiService::class.java)

        val id = stateHandle.get<Int>("restaurant_id") ?: 0
        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(2)
            state.value = restaurant
        }
    }


    private suspend fun getRemoteRestaurant(id: Int) : Restaurant {
        return withContext(Dispatchers.IO) {
            val responseMap  = restInterface.getRestaurant(id = id)
            return@withContext responseMap.values.first()
        }
    }
}