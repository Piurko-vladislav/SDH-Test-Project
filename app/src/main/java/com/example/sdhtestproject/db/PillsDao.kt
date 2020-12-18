package com.example.sdhtestproject.db

import androidx.room.*
import com.example.sdhtestproject.models.DbResults
import com.example.sdhtestproject.models.Results
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PillsDao {
    @Insert
    fun insert(results: DbResults) :Completable

    @Update
    fun update(results: DbResults): Completable

    @Delete
    fun delete(results: DbResults): Completable

    @Query("delete from results")
    fun deleteAllResults()

    @Query("select * from results order by id desc")
    fun getAllResults(): Observable<List<DbResults>>

    @Query("SELECT * FROM results WHERE id = :id")
    fun getAllResults(id:Int): Observable<List<DbResults>>
}