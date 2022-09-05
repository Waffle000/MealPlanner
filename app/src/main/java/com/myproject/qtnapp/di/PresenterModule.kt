package com.myproject.qtnapp.di

import com.myproject.qtnapp.ui.category.CategoryPresenter
import com.myproject.qtnapp.ui.category.CategoryView
import org.koin.dsl.module


val presenterModule = module{
    factory { (v: CategoryView)->
        CategoryPresenter(get(),v)
    }
}