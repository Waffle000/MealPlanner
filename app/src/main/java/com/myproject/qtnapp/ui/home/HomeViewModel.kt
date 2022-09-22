package com.myproject.qtnapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.log

class HomeViewModel(private val repository: AppRepository): BaseViewModel() {
    private val successGetFood = MutableLiveData<SingleLiveEvent<List<FoodByCategoryEntity>>>()
    fun observeSuccessGetFood(): LiveData<SingleLiveEvent<List<FoodByCategoryEntity>>> = successGetFood

    fun deleteAllFood(list: MutableSet<String>) {
        viewModelScope.launch {
            when(val result = repository.deleteAllFood()) {
                is ResponseResult.Success -> {
                    getFoodByCategory(list)
                }

                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }
    fun getFoodByCategory(list: MutableSet<String>) {
        viewModelScope.launch {
            list.map { category ->
                val getFood = async { repository.getFoodByCategoryRemote(category) }
                when(val result = getFood.await()) {
                    is ResponseResult.Success -> {
                        val listFood = result.data.meals.map {
                            FoodByCategoryEntity(
                                id = it.idMeal ?: "",
                                mealName = it.strMeal,
                                imageLink = it.strMealThumb,
                                category = category,
                                fat = Random().nextInt(13 - 2) + 2,
                                pro = Random().nextInt(8 - 2) + 2,
                                carb = Random().nextInt(6 - 1) + 1,
                                isFav = false
                            )
                        }
                        repository.insertFoodByCategory(listFood)
                    }
                    is ResponseResult.Error -> {
                        isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                    }
                }
            }
            getFoodByCategoryLocal()
        }
    }

    fun getFoodByCategoryLocal() {
        viewModelScope.launch {
            when(val result = repository.getAllFoodByCategory()) {
                is ResponseResult.Success -> {
                    successGetFood.postValue(SingleLiveEvent(result.data))
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }

    fun insertHistory(history: HistoryEntity) {
        viewModelScope.launch {
            repository.insertHistory(history)
        }
    }
}