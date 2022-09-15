package com.myproject.qtnapp.data.repository

import android.util.Log
import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.local.dao.FoodByCategoryDao
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.FoodByCategoryResponse
import com.myproject.qtnapp.data.remote.RemoteRepository
import io.reactivex.Flowable
import io.reactivex.Observable

class AppRepository(private val local: LocalRepository, private val remote: RemoteRepository) {
    fun getCategories() = remote.getCategories()

    suspend fun insertUser(user: UserEntity) = local.insertUser(user)

    suspend fun getDataLogin(email: String, password: String): UserEntity? {
        return local.getLogin(email, password)
    }

    suspend fun checkEmail(email: String): UserEntity? {
        return local.checkEmail(email)
    }

    suspend fun updateUser(user: UserEntity) = local.updateUser(user)

    suspend fun insertFoodByCategory(list: List<FoodByCategoryEntity>) = local.insertFoodByCategory(list)

    suspend fun getAllFoodByCategory() : List<FoodByCategoryEntity> {
        return local.getAllFoodCategory()
    }

//    fun getFoodByCategoryremote(category: String) : Observable<FoodByCategoryResponse> {
//    }
}