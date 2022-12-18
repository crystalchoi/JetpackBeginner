package com.codingtroops.restaurantsapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingtroops.restaurantsapp.model.Restaurant

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)
}