package com.example.sdhtestproject.contracts

import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.utils.RequestType

interface PillsListContract {

    interface View {

        fun showListOfPills(pillsList: List<Results>?)

        fun hasNextPage(isHasNext: Boolean)

        fun hasPreviousPage(isHasPrevious : Boolean)
    }

    interface Presenter {

        fun getListOfPills(hasInternetConnection : Boolean)

        fun getListOfPillsByPage(requestType: RequestType, hasInternetConnection : Boolean)

        fun takeView(view: View)

        fun dropView()

    }


}