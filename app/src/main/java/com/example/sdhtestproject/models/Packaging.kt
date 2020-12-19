package com.example.sdhtestproject.models

data class Packaging(
    val id: Int?,
    val composition: Composition?,
    val description: String?,
    val inBulk: Boolean?,
    val min_quantity: String?,
    val package_quantity: String?,
    val variant: Variant?
) {

}