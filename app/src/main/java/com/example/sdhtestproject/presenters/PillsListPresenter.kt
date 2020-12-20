package com.example.sdhtestproject.presenters

import android.annotation.SuppressLint
import com.example.sdhtestproject.contracts.PillsListContract
import com.example.sdhtestproject.models.*
import com.example.sdhtestproject.repositotys.PillsDaoRepository
import com.example.sdhtestproject.repositotys.PillsRepository
import com.example.sdhtestproject.utils.RequestType
import com.example.sdhtestproject.utils.RetrofitUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PillsListPresenter(
    private val daoRepository: PillsDaoRepository,
    private val pillsRepository: PillsRepository
) : PillsListContract.Presenter {

    private var view: PillsListContract.View? = null
    private var response: Response? = null

    @SuppressLint("CheckResult")
    override fun getListOfPills(hasInternetConnection: Boolean) {
        if (hasInternetConnection) {
            pillsRepository.getPillsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    view?.showListOfPills(result.results)
                    response = result
                    view?.hasNextPage(result.next != null)
                    view?.hasPreviousPage(result.previous != null)
                    response?.results?.forEach {
                        daoRepository.insert(it)
                    }
                }, { error ->
                    error.printStackTrace()
                })

        } else {
            daoRepository.getAllResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ resault ->
                    view?.showListOfPills(resault)
                }, { error ->
                    error.printStackTrace()
                })
        }
    }


    @SuppressLint("CheckResult")
    override fun getListOfPillsByPage(requestType: RequestType, hasInternetConnection: Boolean) {
        val page: String? = when (requestType) {
            RequestType.NEXT ->
                response?.next
            RequestType.PREVIOUS ->
                response?.previous
        }
        if (page != null) {
            if (page == RetrofitUtils.BASE_URL + "v1/medicine/")
                getListOfPills(hasInternetConnection)
            else {
                val pageNumber =
                    page.removePrefix(RetrofitUtils.BASE_URL + "v1/medicine/?page=").toInt()
                pillsRepository.getPillsListByPage(pageNumber)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ resault ->
                        view?.showListOfPills(resault.results)
                        response = resault
                        view?.hasNextPage(resault.next != null)
                        view?.hasPreviousPage(resault.previous != null)

                    }, { error ->
                        error.printStackTrace()
                    })
            }
        }


    }

    override fun takeView(view: PillsListContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

}