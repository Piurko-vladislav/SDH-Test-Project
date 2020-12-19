package com.example.sdhtestproject.models

data class Response(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Results>? = null
) {
}