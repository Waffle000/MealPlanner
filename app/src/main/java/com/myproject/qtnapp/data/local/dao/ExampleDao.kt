package com.myproject.qtnapp.data.local.dao

import android.os.Parcelable
import androidx.room.*
import com.myproject.qtnapp.data.local.entity.ExampleEntity
import kotlinx.android.parcel.Parcelize

@Dao
interface ExampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExample(example : ExampleEntity)
}