package com.example.sdhtestproject.utils

import com.example.sdhtestproject.network.RetrofitClient
import com.example.sdhtestproject.network.RetrofitServices

object RetrofitUtils {
    val BASE_URL: String = "https://api.pills-prod.sdh.com.ua/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}