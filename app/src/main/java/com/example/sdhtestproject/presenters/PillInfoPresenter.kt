package com.example.sdhtestproject.presenters

import android.annotation.SuppressLint
import com.example.sdhtestproject.contracts.PillInfoContract
import com.example.sdhtestproject.models.*
import com.example.sdhtestproject.repositotys.PillsDaoRepositoryProvider
import com.example.sdhtestproject.repositotys.PillsRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PillInfoPresenter() : PillInfoContract.Presenter {

    val daoRepository = PillsDaoRepositoryProvider.providePillsDaoRepository()
    var pillsRepository = PillsRepositoryProvider.providePillsRepository()
    private var view: PillInfoContract.View? = null
    private var responce: Responce? = null

    @SuppressLint("CheckResult")
    override fun getPillInfo(id: Int?, hasInternetConnection: Boolean) {
        if (hasInternetConnection) {
            if (id != null) {
                pillsRepository.getPillInfo(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ resault ->
                        view?.showPillInfo(resault)
                    }, { error ->
                        error.printStackTrace()
                    })
            }
        } else {
            daoRepository.getAllResultsById(id!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ resault ->
                    view?.showPillInfo(dbResultsToResults(resault))
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    fun dbResultsToResults(dbResults: List<DbResults>): Results {
        val result: Results
        var it = dbResults.get(0)
        result = Results(
            it.id,
            Composition(
                null, it.db_composition_discription, null,
                Inn(null, it.db_composition_inn_name),
                PharmForm(null, it.db_composition_pharm_form_name, null),
                null, null
            ),
            Packaging(
                null, null, it.db_packaging_name,
                null, null, null, null
            ),
            Trade_label(
                null,
                it.db_trade_label
            ),
            Manufacturer(null, it.db_manufacturer_name, null),
            null
        )
        return result
    }

    override fun takeView(view: PillInfoContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

}