package com.example.core.data.source.local

import com.example.core.data.source.local.entity.*
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = movieDao.getMovieDetail(id)

    fun getMovieFavorite(): Flow<List<MovieDetailEntity>> = movieDao.getMovieFavorite()

    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity) = movieDao.insertMovieDetail(movieDetailEntity)

    fun updateMovieDetail(movieDetailEntity: MovieDetailEntity, newState: Boolean) {
        movieDetailEntity.favorite = newState
        movieDao.updateMovieDetail(movieDetailEntity)
    }

    fun getMovieNowPlaying(): Flow<List<MovieNowPlayingEntity>> = movieDao.getMovieNowPlaying()

    suspend fun insertMovieNowPlaying(movieNowPlayingEntity: List<MovieNowPlayingEntity>) = movieDao.insertMovieNowPlaying(movieNowPlayingEntity)

    fun getMoviePopular(): Flow<List<MoviePopularEntity>> = movieDao.getMoviePopular()

    suspend fun insertMoviePopular(moviePopularEntity: List<MoviePopularEntity>) = movieDao.insertMoviePopular(moviePopularEntity)

    fun getMovieRecommendations(id: Int): Flow<List<MovieRecommendationsEntity>> = movieDao.getMovieRecommendations(id)

    suspend fun insertMovieRecommendations(movieRecommendationsEntity: List<MovieRecommendationsEntity>) = movieDao.insertMovieRecommendations(movieRecommendationsEntity)

    fun getMovieSearch(query: String): Flow<List<MovieSearchEntity>> = movieDao.getMovieSearch(query)

    suspend fun insertMovieSearch(movieSearchEntity: List<MovieSearchEntity>) = movieDao.insertMovieSearch(movieSearchEntity)

    fun getMovieSimilar(id: Int): Flow<List<MovieSimilarEntity>> = movieDao.getMovieSimilar(id)

    suspend fun insertMovieSimilar(movieSimilarEntity: List<MovieSimilarEntity>) = movieDao.insertMovieSimilar(movieSimilarEntity)

    fun getMovieTopRated(): Flow<List<MovieTopRatedEntity>> = movieDao.getMovieTopRated()

    suspend fun insertMovieTopRated(movieTopRatedEntity: List<MovieTopRatedEntity>) = movieDao.insertMovieTopRated(movieTopRatedEntity)

    fun getMovieUpcoming(): Flow<List<MovieUpcomingEntity>> = movieDao.getMovieUpcoming()

    suspend fun insertMovieUpcoming(movieUpcomingEntity: List<MovieUpcomingEntity>) = movieDao.insertMovieUpcoming(movieUpcomingEntity)
}