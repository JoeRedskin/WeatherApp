package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CityApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CityApplication)
            modules(repositoryModule, netModule, databaseModule)
        }
    }
}