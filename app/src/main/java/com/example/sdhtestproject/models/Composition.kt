package com.example.sdhtestproject.models

data class Composition(
    val id: Int?,
    val discription: String?,
    val act: List<String>?,
    val inn: Inn?,
    val pharm_form: PharmForm?,
    val dosage: Double?,
    val measure: Measure?
) {

}
