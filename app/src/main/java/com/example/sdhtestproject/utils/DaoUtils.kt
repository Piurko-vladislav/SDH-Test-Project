package com.example.sdhtestproject.utils

import com.example.sdhtestproject.db.AppDatabase
import com.example.sdhtestproject.db.PillsDao

object DaoUtils {
    val dao: PillsDao
        get() = AppDatabase.INSTANCE!!.pillsDao()
}

