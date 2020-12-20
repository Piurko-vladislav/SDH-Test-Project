package com.example.sdhtestproject.presenters

import android.annotation.SuppressLint
import com.example.sdhtestproject.contracts.PillInfoContract
import com.example.sdhtestproject.repositotys.PillsDaoRepository
import com.example.sdhtestproject.repositotys.PillsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PillInfoPresenter(
    private val daoRepository: PillsDaoRepository,
    private val pillsRepository: PillsRepository
) : PillInfoContract.Presenter {

    private var view: PillInfoContract.View? = null

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
                    view?.showPillInfo(resault.get(0))
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    override fun takeView(view: PillInfoContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

}