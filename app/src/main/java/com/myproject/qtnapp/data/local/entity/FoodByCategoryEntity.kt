package com.myproject.qtnapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food_db")
class FoodByCategoryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_meal")
    val id: String,
    @ColumnInfo(name = "meal_name")
    val mealName: String?,
    @ColumnInfo(name = "image_link")
    val imageLink: String?,
    @ColumnInfo(name = "category")
    val category: String?
) : Parcelable