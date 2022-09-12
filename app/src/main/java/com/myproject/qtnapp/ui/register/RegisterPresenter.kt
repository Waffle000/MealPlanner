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

    fun checkEmail(user:UserEntity) {
        CompositeDisposable().add(
            repository.checkEmail(user.email ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                   if (it == null) {
                       Log.e("TAG","SUCCESS")
                   } else {
                       Log.e("TAG", "ERROR")
                   }
                },this::onError)
        )
    }

    private fun onError(t:Throwable){
        view.onError(t)
    }
}

interface RegisterView {
    fun onError(t: Throwable)
    fun successInsert(success : Boolean)
}