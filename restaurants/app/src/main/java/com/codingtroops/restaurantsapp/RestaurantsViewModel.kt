package com.codingtroops.restaurantsapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingtroops.restaurantsapp.api.RestaurantsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.model.dummyRestaurants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RestaurantViewModel"
private const val BASE_URL = "https://restaurants-3ac64-default-rtdb.asia-southeast1.firebasedatabase.app/"

class RestaurantsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private var restInterface: RestaurantsApiService
    val state = mutableStateOf(emptyList<Restaurant>())
//    val state = mutableStateOf(dummyRestaurants.restoreSelections())
    private lateinit var restaurantCall : Call<List<Restaurant>>


    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        restInterface = retrofit.create(RestaurantsApiService::class.java)

        getRestaurants()
    }




    fun toggleFavorite(id: Int) {
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES)
            .orEmpty().toMutableList()
        if (item.isFavorite) savedToggled.add(item.id)
        else savedToggled.remove(item.id)
        stateHandle[FAVORITES] = savedToggled
    }

    private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
        stateHandle.get<List<Int>?>(FAVORITES)?.let { selectedIds ->
            val restaurantsMap = this.associateBy { it.id }
            selectedIds.forEach { id ->
                restaurantsMap[id]?.isFavorite = true
            }
            return restaurantsMap.values.toList()
        }
        return this
    }

    private fun getRestaurants() {
        Log.d(TAG, "getRestaurants()")
        restaurantCall = restInterface.getRestaurants()
        restaurantCall.enqueue(
            object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    Log.d(TAG, "onResponse $response")

                    response.body()?.let { restaurants ->
                        Log.d(TAG, "response: $restaurants.size")
                        state.value = restaurants.restoreSelections()

                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                    t.printStackTrace()
                }
            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        restaurantCall.cancel()
    }

    companion object {
        const val FAVORITES = "favorites"
    }

}