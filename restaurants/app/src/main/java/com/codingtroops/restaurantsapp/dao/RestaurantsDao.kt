package com.codingtroops.restaurantsapp.dao

import androidx.room.*
import com.codingtroops.restaurantsapp.model.PartialRestaurant
import com.codingtroops.restaurantsapp.model.Restaurant

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)

    @Update(entity = Restaurant::class)
    suspend fun update(partialRestaurant: PartialRestaurant)
}