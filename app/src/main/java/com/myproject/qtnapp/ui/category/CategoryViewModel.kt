package com.myproject.qtnapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: AppRepository) : BaseViewModel() {
    private val getCategorySuccess = MutableLiveData<SingleLiveEvent<CategoriesResponse?>>()
    fun observeGetCategory() : LiveData<SingleLiveEvent<CategoriesResponse?>> = getCategorySuccess

    private val updateUser= MutableLiveData<SingleLiveEvent<Boolean>>()
    fun observeUpdateUser() : LiveData<SingleLiveEvent<Boolean>> = updateUser

    fun getCategoryRemote() {
        viewModelScope.launch {
            when(val result = repository.getCategories()) {
                is ResponseResult.Success -> {
                    getCategorySuccess.postValue(SingleLiveEvent(result.data))
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }

        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            repository.updateUser(user)
            updateUser.postValue(SingleLiveEvent(true))
        }
    }


}