package com.myproject.qtnapp.data.repository

import android.util.Log
import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.remote.RemoteRepository
import io.reactivex.Flowable
import java.util.*

class AppRepository(private val local: LocalRepository, private val remote: RemoteRepository) {
    fun getCategories() = remote.getCategories()

    fun insertUser(user: UserEntity) = local.insertUser(user)

    fun getDataLogin(email: String, password: String) : Flowable<UserEntity?> {
        Log.e("TAG2", "${local.getLogin(email,password)}")
        return local.getLogin(email, password)
    }

    fun checkEmail(email: String) : Flowable<UserEntity> {
        return local.checkEmail(email)
    }

    fun updateUser(user: UserEntity) = local.updateUser(user)
}