package com.myproject.qtnapp.data.local.dao

import androidx.room.*
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FoodByCategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFoodByCategory(list: List<FoodByCategoryEntity>)

    @Query("SELECT * FROM food_db")
    suspend fun getAllFood() : List<FoodByCategoryEntity>

    @Query("SELECT * FROM food_db WHERE isFav == 1")
    suspend fun getFavoriteFood() : List<FoodByCategoryEntity>

    @Update
    suspend fun updateFood(food: FoodByCategoryEntity)

    @Query("DELETE FROM food_db")
    suspend fun deleteAllFood()
}