package com.example.sdhtestproject.repositotys

import com.example.sdhtestproject.models.Response
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.network.RetrofitServices
import io.reactivex.Single

class PillsRepository(private val retrofitServices: RetrofitServices) {

    fun getPillsList(): Single<Response> {
        return retrofitServices.getPillsList()
    }
    fun getPillsListByPage(page:Int): Single<Response> {
        return retrofitServices.getPillsListByPage(page)
    }

    fun searchPills(title: String): Single<Response>  {
        return retrofitServices.searchPills(title)
    }

    fun getPillInfo(id : Int): Single<Results>{
        return retrofitServices.getPillInfo(id)
    }

}
