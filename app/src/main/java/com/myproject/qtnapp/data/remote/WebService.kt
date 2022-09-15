package com.myproject.qtnapp.data.remote

import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.model.response.FoodByCategoryResponse
import com.myproject.qtnapp.data.model.response.FoodDetailResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories() : Response<CategoriesResponse>

    @GET("api/json/v1/1/filter.php?")
    suspend fun getFoodByCategory(@Query("c")category: String) : Response<FoodByCategoryResponse>

    @GET("api/json/v1/1/lookup.php?")
    suspend fun getFOodDetailById(@Query("i")mealId: String) : Response<FoodDetailResponse>
}