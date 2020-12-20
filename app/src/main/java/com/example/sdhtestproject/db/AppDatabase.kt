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

}