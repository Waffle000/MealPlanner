package com.myproject.qtnapp.di

import androidx.lifecycle.ViewModel
import com.myproject.qtnapp.ui.category.CategoryViewModel
import com.myproject.qtnapp.ui.favorite.FavoriteViewModel
import com.myproject.qtnapp.ui.history.HistoryViewModel
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
    single { FavoriteViewModel(get()) }
    single { HistoryViewModel(get()) }
}