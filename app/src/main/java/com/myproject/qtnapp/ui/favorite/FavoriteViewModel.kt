package com.myproject.qtnapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: AppRepository) : BaseViewModel()  {
    private val favoriteFood= MutableLiveData<SingleLiveEvent<List<FoodByCategoryEntity>>>()
    fun observeFoodFavorite() : LiveData<SingleLiveEvent<List<FoodByCategoryEntity>>> = favoriteFood

    fun getFavoriteFood() {
        viewModelScope.launch {
            when(val result = repository.getFavoriteFood()) {
                is ResponseResult.Success -> {
                    favoriteFood.postValue(SingleLiveEvent(result.data))
                }

                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }
}