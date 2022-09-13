package com.myproject.qtnapp.ui.login

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(private val repository: AppRepository, private val view: LoginView) :
    LifecycleObserver {
    fun getDataLogin(email: String, password: String) {
        CompositeDisposable().add(
            repository.getDataLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isEmpty()) {
                        view.errorLogin(true)
                    } else {
                        it.map { user ->
                            view.successLogin(user)
                        }
                    }
                }, this::onError)
        )
    }

    private fun onError(t: Throwable) {
        Log.e("TAG1", "$t")
        view.onError(t)
    }
}

interface LoginView {
    fun onError(t: Throwable)
    fun errorLogin(failed: Boolean)
    fun successLogin(data: UserEntity)
}