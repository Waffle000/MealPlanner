package com.myproject.qtnapp.data.local.dao

import androidx.room.*
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FoodByCategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFoodByCategory(list: List<FoodByCategoryEntity>)

    @Query("SELECT * FROM food_db")
    suspend fun getAllFood() : List<FoodByCategoryEntity>

    @Query("DELETE FROM food_db")
    suspend fun deleteAllFood()
}