package com.example.sdhtestproject.di.modules

import android.content.Context
import com.example.sdhtestproject.app.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppContextModule (val app : MyApplication) {

    @Provides
    fun provideApplicationContext() : Context{
        return app.applicationContext
    }
}