package com.myproject.qtnapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "history_db")
class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "fat")
    val fat: Int?,
    @ColumnInfo(name = "pro")
    val pro: Int?,
    @ColumnInfo(name = "carb")
    val carb: Int?,
    @ColumnInfo(name = "isComplete")
    val isComplete: Boolean
): Parcelable