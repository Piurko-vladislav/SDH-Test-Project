package com.example.sdhtestproject.network

import com.example.sdhtestproject.models.Response
import com.example.sdhtestproject.models.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    //https://api.pills-prod.sdh.com.ua/v1/medicine/
    @GET("v1/medicine/")
    fun getPillsList(): Single<Response>

    //https://api.pills-prod.sdh.com.ua/v1/medicine/?page=page
    @GET("v1/medicine/")
    fun getPillsListByPage(@Query("page")page: Int): Single<Response>

    //https://api.pills-prod.sdh.com.ua/v1/medicine/?search=<назва препарату>
    @GET("v1/medicine/")
    fun searchPills(@Query("search")name: String): Single<Response>

    //v1/medicine/<id>
    @GET("v1/medicine/{id}")
    fun getPillInfo(@Path("id")id: Int) : Single<Results>
}