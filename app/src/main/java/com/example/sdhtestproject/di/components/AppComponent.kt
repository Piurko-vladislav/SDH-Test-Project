package com.example.sdhtestproject.di.components

import com.example.sdhtestproject.di.modules.AppContextModule
import com.example.sdhtestproject.di.modules.DatabaseModule
import com.example.sdhtestproject.di.modules.NetworkModule
import com.example.sdhtestproject.views.PillInfoFragment
import com.example.sdhtestproject.views.PillsListFragment
import com.example.sdhtestproject.views.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppContextModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(pillsListFragment: PillsListFragment)
    fun inject(pillsInfoFragment: PillInfoFragment)
    fun inject(searchFragment: SearchFragment)
}