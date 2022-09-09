package com.myproject.qtnapp.di

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val sharedPrefFile = "kotlinshared"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

    fun isLogin(login_key: String, login: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(login_key, login)
        editor.apply()
    }

    fun categoryData(category_key: String, category: MutableSet<String>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putStringSet(category_key, category)
        editor.apply()
    }

    fun getCategoryData(category_key: String): MutableSet<String>? {
        return sharedPreferences.getStringSet(category_key, null)
    }
    fun getIsLogin(login_key: String) : Boolean {
        return sharedPreferences.getBoolean(login_key, false)
    }

    fun isLogout(login_key: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(login_key).apply()
    }
}