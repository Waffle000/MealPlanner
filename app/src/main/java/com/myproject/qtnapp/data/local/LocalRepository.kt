package com.myproject.qtnapp.data.local

import com.myproject.qtnapp.data.local.entity.UserEntity

class LocalRepository(private val appDataBase: AppDataBase) {

    private fun <T> getResult(request: () -> T): T {
        val res = request.invoke()
        return res
    }

    private suspend fun <T> getResultSus(request: suspend () -> T): T {
        val res = request.invoke()
        return res
    }

    fun insertUser(user: UserEntity) = getResult {
        appDataBase.userDao().insertUser(user)
    }

    fun getLogin(email: String, password: String) = getResult {
        appDataBase.userDao().getDataLogin(email, password)
    }

    suspend fun getLoginSus(email: String, password: String) = getResultSus {
        appDataBase.userDao().getDataLoginSus(email, password)
    }

    fun checkEmail(email: String) = getResult {
        appDataBase.userDao().checkEmail(email)
    }

    fun updateUser(user: UserEntity) = getResult {
        appDataBase.userDao().updateUser(user)
    }
}