package com.example.sdhtestproject.di.modules

import android.content.Context
import androidx.room.Room
import com.example.sdhtestproject.db.AppDatabase
import com.example.sdhtestproject.db.PillsDao
import com.example.sdhtestproject.repositotys.PillsDaoRepository
import com.example.sdhtestproject.utils.DbUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppContextModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext : Context) : AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DbUtils.dbName).build()
    }

    @Provides
    fun provideDao(db: AppDatabase): PillsDao{
        return db.pillsDao()
    }

    @Provides
    @Singleton
    fun providePillsDaoRepository(dao: PillsDao): PillsDaoRepository {
        return PillsDaoRepository(dao)
    }
}