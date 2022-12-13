package com.myproject.qtnapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: AppRepository) : BaseViewModel() {
    private val profileSuccess = MutableLiveData<SingleLiveEvent<UserEntity>>()
    fun observeProfileSuccess(): LiveData<SingleLiveEvent<UserEntity>> = profileSuccess

    fun getProfile(email: String) {
        viewModelScope.launch {
            when(val result = repository.checkEmail(email)) {
                is ResponseResult.Success -> {
                    profileSuccess.postValue(SingleLiveEvent(result.data) as SingleLiveEvent<UserEntity>?)
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }
}