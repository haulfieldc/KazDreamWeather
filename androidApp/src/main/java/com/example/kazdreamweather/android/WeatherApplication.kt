package com.example.kazdreamweather.android

import android.app.Application
import com.example.kazdreamweather.android.di.appModule
import com.example.kazdreamweather.di.getSharedModules
import org.koin.core.context.startKoin

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}