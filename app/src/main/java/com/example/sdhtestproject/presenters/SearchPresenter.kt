package com.example.sdhtestproject.presenters

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView
import com.example.sdhtestproject.contracts.SearchContract
import com.example.sdhtestproject.repositotys.PillsRepositoryProvider
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SearchPresenter : SearchContract.Presenter {

    private val pillsRepository = PillsRepositoryProvider.providePillsRepository()
    private var view: SearchContract.View? = null

    @SuppressLint("CheckResult")
    override fun startSearching(searchView: SearchView) {
        Observable
            .create(ObservableOnSubscribe<String> { subscriber ->
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        subscriber.onNext(newText!!)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        subscriber.onNext(query!!)
                        return false
                    }
                })
            })
            .map { text -> text.toLowerCase().trim() }
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .subscribe { text ->
                pillsRepository.searchPills(text)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ resault ->
                        view?.showListOfPills(resault.results)
                    }, { error ->
                        error.printStackTrace()
                    })
            }
    }

    override fun takeView(view: SearchContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }
}