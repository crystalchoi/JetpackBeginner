package com.codingtroops.restaurantsapp.model

import android.text.TextUtils.isEmpty
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.RestaurantsApplication
import com.codingtroops.restaurantsapp.api.RestaurantsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.model.dummyRestaurants
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException


const val BASE_URL = "https://restaurants-3ac64-default-rtdb.asia-southeast1.firebasedatabase.app/"


private const val TAG = "RestaurantViewModel"

class RestaurantsViewModel(
//    private val stateHandle: SavedStateHandle
    ) : ViewModel() {
    private var restInterface: RestaurantsApiService
    private var restaurantsDao =  RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())
    val state = mutableStateOf(emptyList<Restaurant>())
//    val state = mutableStateOf(dummyRestaurants.restoreSelections())
//    private lateinit var restaurantCall : Call<List<Restaurant>>
//    var job = Job()
//    private val scope = CoroutineScope(context = job + Dispatchers.IO)

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        restInterface = retrofit.create(RestaurantsApiService::class.java)

        getRestaurants()
    }


    fun toggleFavorite(id: Int, oldValue: Boolean) {
//        val restaurants = state.value.toMutableList()
//        val itemIndex = restaurants.indexOfFirst { it.id == id }
//        val item = restaurants[itemIndex]
//        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
//        storeSelection(restaurants[itemIndex])
//        state.value = restaurants
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = toggleFavoriteRestaurant(id, oldValue)
            state.value = updatedRestaurants
        }
    }

    private suspend fun toggleFavoriteRestaurant(id: Int, oldValue: Boolean) =
        withContext(Dispatchers.IO) {
            restaurantsDao.update(PartialRestaurant(id = id, isFavorite = !oldValue))
            restaurantsDao.getAll()
        }

//    private fun storeSelection(item: Restaurant) {
//        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES).orEmpty().toMutableList()
//        if (item.isFavorite) savedToggled.add(item.id)
//        else savedToggled.remove(item.id)
//        stateHandle[FAVORITES] = savedToggled
//    }

//    private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
//        stateHandle.get<List<Int>?>(FAVORITES)?.let { selectedIds ->
//            val restaurantsMap = this.associateBy { it.id }
//                .toMutableMap()
//            selectedIds.forEach { id ->
//                val restaurant = restaurantsMap[id] ?: return@forEach
//                restaurantsMap[id] = restaurant.copy(isFavorite = true)
//                 Log.d(TAG, "restoreSelections id: $id")
//            }
//            return restaurantsMap.values.toList()
//        }
//        return this
//    }

    private suspend fun getAllRestaurants() : List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
              refreshCache()
            } catch(ex: Exception){
                when (ex) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
//                        return@withContext restaurantsDao.getAll()
                        if (restaurantsDao.getAll().isEmpty())
                            throw Exception("Something went worng. " + "We have no data.")
                    }
                    else -> throw ex
                }
            }
            return@withContext restaurantsDao.getAll()
        }
    }


    private suspend fun refreshCache() {
        val restaurants = restInterface.getRestaurants()

        val favoriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(restaurants)
        restaurantsDao.updateAll(
            favoriteRestaurants.map {
                PartialRestaurant(it.id, true)
            }
        )
    }

    private  fun getRestaurants() {
        Log.d(TAG, "getRestaurants()")
        viewModelScope.launch(errorHandler) {
            state.value = getAllRestaurants()
        }

//        restaurantCall = restInterface.getRestaurants()
//        restaurantCall.enqueue(
//            object : Callback<List<Restaurant>> {
//                override fun onResponse(
//                    call: Call<List<Restaurant>>,
//                    response: Response<List<Restaurant>>
//                ) {
//                    Log.d(TAG, "onResponse $response")
//
//                    response.body()?.let { restaurants ->
//                        Log.d(TAG, "response: $restaurants.size")
//                        state.value = restaurants.restoreSelections()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
//                    Log.d(TAG, "onFailure")
//                    t.printStackTrace()
//                }
//            }
//        )
    }


    override fun onCleared() {
        super.onCleared()
//        restaurantCall.cancel()
//        job.cancel()
    }

//    companion object {
//        const val FAVORITES = "favorites"
//    }

}