package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieDetailEntities")
data class MovieDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)