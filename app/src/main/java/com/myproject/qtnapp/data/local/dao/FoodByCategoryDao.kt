package com.myproject.qtnapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FoodByCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodByCategory(list: List<FoodByCategoryEntity>): Completable

    @Query("SELECT * FROM food_db")
    fun getAllFood() : Observable<List<FoodByCategoryEntity>>
}