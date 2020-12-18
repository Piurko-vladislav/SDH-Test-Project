package com.example.sdhtestproject.contracts

import com.example.sdhtestproject.models.Results

import androidx.appcompat.widget.SearchView

interface SearchContract {

    interface View {

        fun showListOfPills(pillsList: List<Results>?)

    }

    interface Presenter {

        fun startSearching(searchView: SearchView)

        fun takeView(view: View)

        fun dropView()

    }
}