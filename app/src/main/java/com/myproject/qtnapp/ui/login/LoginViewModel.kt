package com.myproject.qtnapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AppRepository) : BaseViewModel() {

    private val isLogin = MutableLiveData<SingleLiveEvent<UserEntity?>>()
    fun observeIsLogin(): LiveData<SingleLiveEvent<UserEntity?>> = isLogin

    fun getDataLogin(email: String, password: String) {
        viewModelScope.launch {
            when (val result = repository.getDataLogin(email, password)) {
                is ResponseResult.Success -> {
                    isLogin.postValue(SingleLiveEvent(result.data))
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }

    fun getData(email: String, password: String) {
        viewModelScope.launch {
            val get = async { repository.getDataLogin(email, password) }
            when(val result = get.await()) {
                is ResponseResult.Success -> {
                    isLogin.postValue(SingleLiveEvent(result.data))
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }
}