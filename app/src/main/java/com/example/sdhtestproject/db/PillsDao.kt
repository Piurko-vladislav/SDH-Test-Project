package com.example.sdhtestproject.db

import androidx.room.*
import com.example.sdhtestproject.models.Results
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PillsDao {
    @Insert
    fun insert(results: Results) :Completable

    @Update
    fun update(results: Results): Completable

    @Delete
    fun delete(results: Results): Completable

    @Query("delete from results")
    fun deleteAllResults()

    @Query("select * from results order by id desc")
    fun getAllResults(): Observable<List<Results>>

    @Query("SELECT * FROM results WHERE id = :id")
    fun getAllResults(id:Int): Observable<List<Results>>
}