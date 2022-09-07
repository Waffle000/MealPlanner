package com.myproject.qtnapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.myproject.qtnapp.data.local.entity.UserEntity
import io.reactivex.Completable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity) : Completable
}