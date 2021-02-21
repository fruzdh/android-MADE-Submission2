package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "MovieSimilarEntities", primaryKeys = ["movieId", "id"])
data class MovieSimilarEntity(
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String?
)