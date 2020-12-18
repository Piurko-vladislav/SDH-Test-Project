package com.example.sdhtestproject.models

class Responce(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: List<Results>? = null
) {
}