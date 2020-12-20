package com.example.sdhtestproject.app

import android.app.Application
import com.example.sdhtestproject.db.AppDatabase
import com.example.sdhtestproject.di.components.AppComponent
import com.example.sdhtestproject.di.components.DaggerAppComponent
import com.example.sdhtestproject.di.modules.AppContextModule
import com.example.sdhtestproject.di.modules.DatabaseModule
import com.example.sdhtestproject.di.modules.NetworkModule

class MyApplication : Application() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    fun initDaggerComponent(){
        component = DaggerAppComponent
            .builder()
            .appContextModule(AppContextModule(this))
            .databaseModule(DatabaseModule())
            .networkModule(NetworkModule())
            .build()

    }
}