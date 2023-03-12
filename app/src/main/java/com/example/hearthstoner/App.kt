package com.example.hearthstoner

import android.app.Application
import com.example.hearthstoner.di.AppComponent
import com.example.hearthstoner.di.DI
import com.example.hearthstoner.di.DaggerAppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        DI.init(provideAppComponent())
    }

    open fun provideAppComponent(): AppComponent = DaggerAppComponent
        .builder()
        .build()
}