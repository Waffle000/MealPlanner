package com.myproject.qtnapp.data.remote

import com.myproject.qtnapp.base.ResponseResult
import retrofit2.HttpException
import retrofit2.Response

class RemoteRepository(private val webService: WebService) {

    suspend fun <T : Any> getResult (
        request: suspend () -> Response<T>
    ): ResponseResult<T> {
        return try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ResponseResult.Success(body)
            } else {
                ResponseResult.Error(code = response.code(), errorMsg = response.message())
            }
        } catch (e: HttpException) {
            ResponseResult.Error(code = e.code(), errorMsg = e.message())
        }
    }

    suspend fun getCategories() = getResult {
        webService.getCategories()
    }

    suspend fun getFoodByCategory(category: String) = getResult {
        webService.getFoodByCategory(category)
    }

    suspend fun getFoodDetail(mealId: String) = getResult {
        webService.getFOodDetailById(mealId)
    }
}