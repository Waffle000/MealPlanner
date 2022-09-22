package com.myproject.qtnapp.data.local

import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity

class LocalRepository(private val appDataBase: AppDataBase) {

    private suspend fun <T> getResult(request: suspend () -> T): ResponseResult<T> {
        return try {
            val res = request.invoke()
            ResponseResult.Success(res)
        } catch (e: Exception) {
            ResponseResult.Error(errorMsg = e.toString())
        }
    }

    suspend fun insertUser(user: UserEntity) = getResult {
        appDataBase.userDao().insertUser(user)
    }

    suspend fun getLogin(email: String, password: String) = getResult {
        appDataBase.userDao().getDataLogin(email, password)
    }

    suspend fun checkEmail(email: String) = getResult {
        appDataBase.userDao().checkEmail(email)
    }

    suspend fun updateUser(user: UserEntity) = getResult {
        appDataBase.userDao().updateUser(user)
    }

    suspend fun insertFoodByCategory(list: List<FoodByCategoryEntity>) = getResult {
        appDataBase.foodByCategoryDao().insertFoodByCategory(list)
    }

    suspend fun getAllFoodCategory() = getResult {
        appDataBase.foodByCategoryDao().getAllFood()
    }

    suspend fun getFavoriteFood() = getResult {
        appDataBase.foodByCategoryDao().getFavoriteFood()
    }

    suspend fun updateFood(food: FoodByCategoryEntity) = getResult {
        appDataBase.foodByCategoryDao().updateFood(food)
    }

    suspend fun deleteAllFood() = getResult {
        appDataBase.foodByCategoryDao().deleteAllFood()
    }

    suspend fun insertHistory(history: HistoryEntity) = getResult {
        appDataBase.historyDao().insertHistory(history)
    }

    suspend fun getHistory() = getResult {
        appDataBase.historyDao().getHistory()
    }
}