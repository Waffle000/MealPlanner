package com.myproject.qtnapp.ui.meal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.FoodDetailResponse
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single
import kotlinx.coroutines.launch

class MealViewModel(private val repository: AppRepository): BaseViewModel() {

    private val getFoodDetailSuccess = MutableLiveData<SingleLiveEvent<FoodDetailResponse>>()
    fun observeGetFoodDetail(): LiveData<SingleLiveEvent<FoodDetailResponse>> = getFoodDetailSuccess

    fun getMealDetail(mealId: String) {
        viewModelScope.launch {
            when(val result = repository.getFoodDetail(mealId)) {
                is ResponseResult.Success -> {
                    getFoodDetailSuccess.postValue(SingleLiveEvent(result.data))
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }

    fun updateFood(food: FoodByCategoryEntity) {
        viewModelScope.launch {
            Log.e("GAGAGA", "updateFood: $food", )
            repository.updateFood(food)
        }
    }
}