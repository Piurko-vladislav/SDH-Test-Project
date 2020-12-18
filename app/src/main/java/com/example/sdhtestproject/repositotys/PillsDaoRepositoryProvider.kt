package com.example.sdhtestproject.repositotys

import com.example.sdhtestproject.db.AppDatabase
import com.example.sdhtestproject.utils.DaoUtils
import com.example.sdhtestproject.utils.RetrofitUtils

object PillsDaoRepositoryProvider {
    fun providePillsDaoRepository(): PillsDaoRepository {
        return PillsDaoRepository(DaoUtils.dao)
    }
}