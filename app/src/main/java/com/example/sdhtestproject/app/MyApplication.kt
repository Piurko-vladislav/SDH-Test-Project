package com.example.sdhtestproject.app

import android.app.Application
import com.example.sdhtestproject.db.AppDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getAppDataBase(applicationContext)
    }
}