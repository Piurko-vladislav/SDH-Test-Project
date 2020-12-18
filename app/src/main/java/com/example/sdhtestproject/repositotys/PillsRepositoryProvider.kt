package com.example.sdhtestproject.repositotys

import com.example.sdhtestproject.network.RetrofitClient
import com.example.sdhtestproject.network.RetrofitServices
import com.example.sdhtestproject.utils.RetrofitUtils

object PillsRepositoryProvider {
    fun providePillsRepository(): PillsRepository {
        return PillsRepository(RetrofitUtils.retrofitService)
    }
}