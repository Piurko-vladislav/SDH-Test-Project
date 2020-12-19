package com.example.sdhtestproject.repositotys

import com.example.sdhtestproject.utils.DaoUtils

object PillsDaoRepositoryProvider {
    fun providePillsDaoRepository(): PillsDaoRepository {
        return PillsDaoRepository(DaoUtils.dao)
    }
}