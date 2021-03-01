package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("tagline")
    val tagline: String?,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("genres")
    val genres: List<MovieGenreResponse>,

    @field:SerializedName("runtime")
    val runtime: Int?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val poster_path: String?,

    @field:SerializedName("vote_average")
    val vote_average: Float
)