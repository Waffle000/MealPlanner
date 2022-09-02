package com.myproject.qtnapp.di

import androidx.room.Room
import com.myproject.qtnapp.data.local.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }
}