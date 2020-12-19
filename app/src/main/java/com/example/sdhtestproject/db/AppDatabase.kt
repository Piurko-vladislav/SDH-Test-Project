package com.example.sdhtestproject.db

import android.content.Context
import androidx.room.*
import com.example.sdhtestproject.db.converters.ResultTypeConverter
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.utils.DbUtils

@Database(entities = arrayOf(Results::class), version = DbUtils.version)
@TypeConverters(ResultTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun pillsDao(): PillsDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DbUtils.dbName).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}