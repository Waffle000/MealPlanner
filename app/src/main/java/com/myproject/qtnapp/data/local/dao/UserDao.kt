package com.myproject.qtnapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myproject.qtnapp.data.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity) : Completable

    @Query("SELECT * FROM user_db WHERE email= :email AND password= :password")
    fun getDataLogin(email: String, password: String) : Flowable<UserEntity?>
}