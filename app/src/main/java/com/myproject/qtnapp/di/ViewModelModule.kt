package com.myproject.qtnapp.di

import com.myproject.qtnapp.ui.login.LoginViewModel
import com.myproject.qtnapp.ui.register.RegisterViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { LoginViewModel(get()) }
    single { RegisterViewModel(get()) }
}