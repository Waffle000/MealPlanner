package com.myproject.qtnapp.ui.category

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryPresenter(private val repository: AppRepository, private val view:CategoryView) : LifecycleObserver {
    fun getCategories(){
        CompositeDisposable().add(
            repository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           view.getCategories(it)
                },this::onError)
        )
    }

     private fun onError(t:Throwable){
        Log.e("ERROR", "ERROR RESPONSE $t")
    }
}

interface CategoryView{
    fun getCategories(data : CategoriesResponse)
}