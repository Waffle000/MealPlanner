package com.myproject.qtnapp.data.local.dao

import androidx.room.*
import com.myproject.qtnapp.data.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_db WHERE email= :email AND password= :password")
    suspend fun getDataLogin(email: String, password: String) : UserEntity?

    @Query("SELECT * FROM user_db WHERE email= :email")
    suspend fun checkEmail(email: String) : UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity)
}