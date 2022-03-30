package com.example.food

import android.app.Application
import com.example.food.di.menu.menuModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(menuModule))
        }
    }
}