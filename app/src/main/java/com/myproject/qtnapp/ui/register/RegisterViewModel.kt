package com.myproject.qtnapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.BaseViewModel
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AppRepository ): BaseViewModel() {
    private val isRegister = MutableLiveData<SingleLiveEvent<Boolean>>()
    fun observeIsRegister(): LiveData<SingleLiveEvent<Boolean>> = isRegister

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            try {
                repository.insertUser(user)
                isRegister.postValue(SingleLiveEvent(true))
            } catch (e: Throwable) {
                isError.postValue(SingleLiveEvent(e.toString()))
            }
        }
    }

    fun checkingEmail(user: UserEntity) {
        viewModelScope.launch {
            try {
                val result = repository.checkEmail(user.email ?: "")
                if(result == null) {
                    insertUser(user)
                } else {
                    isRegister.postValue(SingleLiveEvent(false))
                }
            } catch (e: Throwable) {
                isError.postValue(SingleLiveEvent(e.toString()))
            }

        }
    }
}