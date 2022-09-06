package com.myproject.qtnapp.data.local

import com.myproject.qtnapp.data.local.entity.UserEntity

class LocalRepository(private val appDataBase: AppDataBase) {
    fun insertUser(user: UserEntity) {
        appDataBase.userDao().insertUser(user)
    }
}