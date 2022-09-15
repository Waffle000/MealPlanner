package com.myproject.qtnapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.BaseViewModel
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AppRepository) : BaseViewModel() {

    private val isLogin = MutableLiveData<SingleLiveEvent<UserEntity?>>()
    fun observeIsLogin(): LiveData<SingleLiveEvent<UserEntity?>> = isLogin

    fun getDataLogin(email: String, password:String) {
        viewModelScope.launch {
            val result = repository.getDataLogin(email, password)
            try {
                isLogin.postValue(SingleLiveEvent(result))
            } catch(e: Throwable) {
                isError.postValue(SingleLiveEvent(e.toString()))
            }
        }
    }
}