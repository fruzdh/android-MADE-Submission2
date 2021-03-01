package com.example.core.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val tagline: String?,
    val release_date: String,
    val genres: String,
    val runtime: Int?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Float,
    var favorite: Boolean
)