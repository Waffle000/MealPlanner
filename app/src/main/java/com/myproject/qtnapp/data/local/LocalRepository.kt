package com.myproject.qtnapp.data.local

import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity

class LocalRepository(private val appDataBase: AppDataBase) {

    private suspend fun <T> getResult(request: suspend () -> T): T {
        val res = request.invoke()
        return res
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
}