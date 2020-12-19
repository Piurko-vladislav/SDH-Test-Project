package com.example.sdhtestproject.contracts

import com.example.sdhtestproject.models.Results

interface PillInfoContract {

    interface View {

        fun showPillInfo(results: Results)
    }

    interface Presenter {

        fun getPillInfo(id: Int?, hasInternetConnection: Boolean)

        fun takeView(view: View)

        fun dropView()
    }
}