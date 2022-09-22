package com.myproject.qtnapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myproject.qtnapp.base.BaseViewModel
import com.myproject.qtnapp.base.ResponseResult
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.data.repository.AppRepository
import com.myproject.qtnapp.utils.SingleLiveEvent
import io.reactivex.Single
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: AppRepository): BaseViewModel() {
    private val getHistorySuccess = MutableLiveData<SingleLiveEvent<List<HistoryEntity>>>()
    fun observeGetHistory(): LiveData<SingleLiveEvent<List<HistoryEntity>>> = getHistorySuccess

    fun getHistory() {
        viewModelScope.launch {
            when(val result = repository.getHistory()) {
                is ResponseResult.Success -> {
                    getHistorySuccess.postValue(SingleLiveEvent(result.data))
                }

                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.errorMsg ?: ""))
                }
            }
        }
    }
}