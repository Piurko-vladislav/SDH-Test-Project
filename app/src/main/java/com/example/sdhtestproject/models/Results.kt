package com.example.sdhtestproject.models


data class Results(
    var id: Int, var composition: Composition? = null,
    var packaging: Packaging? = null,
    var trade_label: Trade_label? = null,
    var manufacturer: Manufacturer? = null,
    var code: String? = null
)