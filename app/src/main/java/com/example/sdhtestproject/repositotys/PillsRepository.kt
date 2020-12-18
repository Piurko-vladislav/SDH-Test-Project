package com.example.sdhtestproject.repositotys

import com.example.sdhtestproject.models.Responce
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.network.RetrofitServices
import io.reactivex.Single

class PillsRepository(val retrofitServices: RetrofitServices) {

    fun getPillsList(): Single<Responce> {
        return retrofitServices.getPillsList()
    }
    fun getPillsListByPage(page:Int): Single<Responce> {
        return retrofitServices.getPillsListByPage(page)
    }

    fun searchPills(title: String): Single<Responce>  {
        return retrofitServices.searchPills(title)
    }

    fun getPillInfo(id : Int): Single<Results>{
        return retrofitServices.getPillInfo(id)
    }

}
