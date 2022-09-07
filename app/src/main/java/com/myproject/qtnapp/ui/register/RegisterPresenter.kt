package com.myproject.qtnapp.ui.register

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterPresenter(private val repository: AppRepository, private val view: RegisterView) : LifecycleObserver {
    fun insertUser(user: UserEntity){
        CompositeDisposable().add(
            repository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.successInsert(true)
                },this::onError)
        )
    }

    private fun onError(t:Throwable){
        Log.e("ERROR", "ERROR RESPONSE $t")
    }
}

interface RegisterView {
    fun successInsert(success : Boolean)
}