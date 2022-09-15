package com.myproject.qtnapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single

abstract class BaseViewModel() : ViewModel() {
    protected val isError = MutableLiveData<SingleLiveEvent<String>>()
    fun observeError() : LiveData<SingleLiveEvent<String>> = isError
}