package com.myproject.qtnapp.data.remote

import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.model.response.FoodByCategoryResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface WebService {
    @GET("api/json/v1/1/categories.php")
    fun getCategories() : Observable<CategoriesResponse>

    @GET("api/json/v1/1/filter.php")
    fun getFoodByCategory() : Observable<FoodByCategoryResponse>
}