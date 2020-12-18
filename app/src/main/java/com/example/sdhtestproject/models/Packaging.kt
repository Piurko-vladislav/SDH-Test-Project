package com.example.sdhtestproject.models

class Packaging(
    var id: Int?,
    var composition: Composition?,
    var description: String?,
    var inBulk: Boolean?,
    var min_quantity: String?,
    var package_quantity: String?,
    var variant: Variant?
) {

}