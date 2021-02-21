package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.*

@Database(entities = [MovieDetailEntity::class, MovieNowPlayingEntity::class, MoviePopularEntity::class,
                     MovieRecommendationsEntity::class, MovieSearchEntity::class, MovieSimilarEntity::class,
                     MovieTopRatedEntity::class, MovieUpcomingEntity::class],
    version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}