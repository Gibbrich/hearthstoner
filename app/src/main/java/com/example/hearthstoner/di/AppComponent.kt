package com.example.hearthstoner.di

import com.example.hearthstoner.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class
    ]
)
interface AppComponent {
    fun inject(entry: MainViewModel)
}