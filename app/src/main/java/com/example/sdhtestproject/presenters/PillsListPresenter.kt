package com.example.sdhtestproject.presenters

import android.annotation.SuppressLint
import com.example.sdhtestproject.contracts.PillsListContract
import com.example.sdhtestproject.models.*
import com.example.sdhtestproject.repositotys.PillsDaoRepositoryProvider
import com.example.sdhtestproject.repositotys.PillsRepositoryProvider
import com.example.sdhtestproject.utils.RequestType
import com.example.sdhtestproject.utils.RetrofitUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PillsListPresenter() : PillsListContract.Presenter {
    val pillsRepository = PillsRepositoryProvider.providePillsRepository()
    val daoRepository = PillsDaoRepositoryProvider.providePillsDaoRepository()

    private var view: PillsListContract.View? = null
    private var responce: Responce? = null

    @SuppressLint("CheckResult")
    override fun getListOfPills(hasInternetConnection: Boolean) {
        if (hasInternetConnection) {
            pillsRepository.getPillsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ resault ->
                    view?.showListOfPills(resault.results)
                    responce = resault
                    view?.hasNextPage(resault.next != null)
                    view?.hasPreviousPage(resault.previous != null)
                    responce?.results?.forEach {
                        daoRepository.insert(DbResults(it.id,
                            it.trade_label?.name,
                            it.manufacturer?.name,
                            it.packaging?.description,
                            it.composition?.discription,
                            it.composition?.inn?.name,
                            it.composition?.pharm_form?.name))
                    }
                }, { error ->
                    error.printStackTrace()
                })

        } else {
            daoRepository.getAllResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ resault ->
                    view?.showListOfPills(dbResultsToResults(resault))
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun dbResultsToResults(dbResults: List<DbResults>): List<Results> {
        val resultList = arrayListOf<Results>()
        dbResults.forEach {
            resultList.add(Results(it.id,
                    Composition(null, it.db_composition_discription,null,
                        Inn(null, it.db_composition_inn_name),
                        PharmForm(null, it.db_composition_pharm_form_name, null),
                        null, null
                    ),
                    Packaging(null, null, it.db_packaging_name,
                        null, null, null, null),
                    Trade_label(null,
                        it.db_trade_label),
                    Manufacturer(null, it.db_manufacturer_name, null),
                null
                )
            )
        }
        return resultList
    }

    @SuppressLint("CheckResult")
    override fun getListOfPillsByPage(requestType: RequestType, hasInternetConnection: Boolean) {
        val page: String? = when (requestType) {
            RequestType.NEXT ->
                responce?.next
            RequestType.PREVIOUS ->
                responce?.previous
        }
        if (page != null) {
            if (page.equals(RetrofitUtils.BASE_URL + "v1/medicine/"))
                getListOfPills(hasInternetConnection)
            else {
                val pageNumber =
                    page.removePrefix(RetrofitUtils.BASE_URL + "v1/medicine/?page=").toInt()
                pillsRepository.getPillsListByPage(pageNumber)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ resault ->
                        view?.showListOfPills(resault.results)
                        responce = resault
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