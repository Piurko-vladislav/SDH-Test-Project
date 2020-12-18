package com.example.sdhtestproject.repositotys

import android.annotation.SuppressLint
import android.util.Log
import com.example.sdhtestproject.db.PillsDao
import com.example.sdhtestproject.models.DbResults
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PillsDaoRepository(private val pillsDao: PillsDao) {

    @SuppressLint("CheckResult")
    fun insert(results: DbResults){
        pillsDao.insert(results)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Insert Success") },
                { Log.d("RxJava", "Insert Error") }
            )
    }

    @SuppressLint("CheckResult")
    fun deleteAllResults() {
        Completable.fromAction{ pillsDao.deleteAllResults() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Delete all Success") },
                { Log.d("RxJava", "Delete all Error") }
            )
    }

    fun getAllResults(): Observable<List<DbResults>> = pillsDao.getAllResults()

    fun getAllResultsById(id:Int): Observable<List<DbResults>> = pillsDao.getAllResults(id)
}