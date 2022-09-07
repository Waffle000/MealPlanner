package com.myproject.qtnapp.di

import com.myproject.qtnapp.ui.category.CategoryPresenter
import com.myproject.qtnapp.ui.category.CategoryView
import com.myproject.qtnapp.ui.login.LoginPresenter
import com.myproject.qtnapp.ui.login.LoginView
import com.myproject.qtnapp.ui.register.RegisterPresenter
import com.myproject.qtnapp.ui.register.RegisterView
import org.koin.dsl.module


val presenterModule = module{
    factory { (v: CategoryView)->
        CategoryPresenter(get(),v)
    }

    factory { (v: RegisterView)->
        RegisterPresenter(get(),v)
    }

    factory { (v: LoginView) ->
        LoginPresenter(get(), v)
    }
}