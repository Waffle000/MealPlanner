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
    fun insertUser(user: UserEntity) : Completable

    @Query("SELECT * FROM user_db WHERE email= :email AND password= :password")
    fun getDataLogin(email: String, password: String) : Flowable<List<UserEntity>>

    @Query("SELECT * FROM user_db WHERE email= :email AND password= :password")
    suspend fun getDataLoginSus(email: String, password: String) : UserEntity?

    @Query("SELECT * FROM user_db WHERE email= :email")
    fun checkEmail(email: String) : Observable<List<UserEntity?>>

    @Update
    fun updateUser(user: UserEntity) : Completable
}