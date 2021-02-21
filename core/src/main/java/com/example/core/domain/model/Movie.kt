package com.example.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?
)