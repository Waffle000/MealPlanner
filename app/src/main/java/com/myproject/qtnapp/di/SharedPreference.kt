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

    fun timeStamp(time_key: String, time: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(time_key, time)
        editor.apply()
    }

    fun proTotal(pro_key: String, pro: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(pro_key, pro)
        editor.apply()
    }

    fun carbTotal(carb_key: String, carb: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(carb_key, carb)
        editor.apply()
    }

    fun fatTotal(fat_key: String, fat: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(fat_key, fat)
        editor.apply()
    }

    fun calorieTotal(calorie_key: String, calorie: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(calorie_key, calorie)
        editor.apply()
    }
    fun categoryData(category_key: String, category: MutableSet<String>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putStringSet(category_key, category)
        editor.apply()
    }

    fun getTimeStamp(time_key: String) : String? {
        return sharedPreferences.getString(time_key, "")
    }

    fun getCalorieData(calorie_key: String) : Int {
        return sharedPreferences.getInt(calorie_key, 0)
    }

    fun getProData(pro_key: String) : Int {
        return sharedPreferences.getInt(pro_key, 0)
    }

    fun getCarbData(carb_key: String) : Int {
        return sharedPreferences.getInt(carb_key, 0)
    }

    fun getFatData(fat_key: String) : Int {
        return sharedPreferences.getInt(fat_key, 0)
    }

    fun getCategoryData(category_key: String): MutableSet<String>? {
        return sharedPreferences.getStringSet(category_key, null)
    }
    fun getIsLogin(login_key: String) : Boolean {
        return sharedPreferences.getBoolean(login_key, false)
    }

    fun clearPro(pro_key: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(pro_key).apply()
    }

    fun clearCarb(carb_key: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(carb_key).apply()
    }

    fun clearFat(fat_key: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(fat_key).apply()
    }

    fun isLogout(login_key: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(login_key).apply()
    }
}