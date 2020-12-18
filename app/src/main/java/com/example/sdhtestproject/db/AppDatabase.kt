package com.example.sdhtestproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sdhtestproject.models.DbResults
import com.example.sdhtestproject.models.Results

@Database(entities = arrayOf(DbResults::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun pillsDao(): PillsDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}