package com.myproject.qtnapp.di

import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.remote.RemoteRepository
import com.myproject.qtnapp.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AppRepository(get(), get())
    }

    single {
        RemoteRepository(get())
    }

    single {
        LocalRepository(get())
    }
}