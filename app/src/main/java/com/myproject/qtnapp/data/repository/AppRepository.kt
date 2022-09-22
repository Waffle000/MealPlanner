package com.myproject.qtnapp.data.repository

import android.util.Log
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.local.dao.FoodByCategoryDao
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.model.response.FoodByCategoryResponse
import com.myproject.qtnapp.data.model.response.FoodDetailResponse
import com.myproject.qtnapp.data.remote.RemoteRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response

class AppRepository(private val local: LocalRepository, private val remote: RemoteRepository) {
    suspend fun insertUser(user: UserEntity) {
        local.insertUser(user)
    }

    suspend fun getDataLogin(email: String, password: String): ResponseResult<UserEntity?> {
        return local.getLogin(email, password)
    }

    suspend fun checkEmail(email: String): ResponseResult<UserEntity?> {
        return local.checkEmail(email)
    }

    suspend fun updateUser(user: UserEntity) = local.updateUser(user)

    suspend fun insertFoodByCategory(list: List<FoodByCategoryEntity>) {
        Log.e("TAGGG", "insertFoodByCategory: $list", )
        local.insertFoodByCategory(list)
    }

    suspend fun getAllFoodByCategory() : ResponseResult<List<FoodByCategoryEntity>>{
        return local.getAllFoodCategory()
    }

    suspend fun getFavoriteFood() : ResponseResult<List<FoodByCategoryEntity>> {
        return local.getFavoriteFood()
    }

    suspend fun updateFood(food: FoodByCategoryEntity) = local.updateFood(food)

    suspend fun deleteAllFood() = local.deleteAllFood()

    suspend fun getCategories() : ResponseResult<CategoriesResponse> {
        return remote.getCategories()
    }

    suspend fun getFoodByCategoryRemote(category: String) : ResponseResult<FoodByCategoryResponse> {
        return remote.getFoodByCategory(category)
    }

    suspend fun getFoodDetail(mealId: String): ResponseResult<FoodDetailResponse> {
        return remote.getFoodDetail(mealId)
    }

    suspend fun insertHistory(history : HistoryEntity) = local.insertHistory(history)

    suspend fun getHistory(): ResponseResult<List<HistoryEntity>> {
        return local.getHistory()
    }

//    fun getFoodByCategoryremote(category: String) : Observable<FoodByCategoryResponse> {
//    }
}