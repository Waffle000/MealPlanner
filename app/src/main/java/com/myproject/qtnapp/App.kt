package com.myproject.qtnapp

import android.app.Application
import com.myproject.qtnapp.di.networkModule
import com.myproject.qtnapp.di.presenterModule
import com.myproject.qtnapp.di.repositoryModule
import com.myproject.qtnapp.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(networkModule, presenterModule, repositoryModule, roomModule))
        }
    }
}