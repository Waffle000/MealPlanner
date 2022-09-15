package com.myproject.qtnapp.di

import com.myproject.qtnapp.ui.category.CategoryViewModel
import com.myproject.qtnapp.ui.home.HomeViewModel
import com.myproject.qtnapp.ui.login.LoginViewModel
import com.myproject.qtnapp.ui.meal.MealViewModel
import com.myproject.qtnapp.ui.register.RegisterViewModel
import io.reactivex.Single
import org.koin.dsl.module

val viewModelModule = module {
    single { LoginViewModel(get()) }
    single { RegisterViewModel(get()) }
    single { CategoryViewModel(get()) }
    single { HomeViewModel(get()) }
    single { MealViewModel(get()) }
}