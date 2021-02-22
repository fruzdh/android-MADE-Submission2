package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.data.source.local.entity.*
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>>

    fun getMovieFavorite(): Flow<List<Movie>>

    fun updateMovieDetail(movieDetail: MovieDetail, newState: Boolean)

    fun getMovieNowPlaying(): Flow<Resource<List<Movie>>>

    fun getMoviePopular(): Flow<Resource<List<Movie>>>

    fun getMovieRecommendations(id: Int): Flow<Resource<List<Movie>>>

    fun getMovieSearch(query: String): Flow<Resource<List<Movie>>>

    fun getMovieSimilar(id: Int): Flow<Resource<List<Movie>>>

    fun getMovieTopRated(): Flow<Resource<List<Movie>>>

    fun getMovieUpcoming(): Flow<Resource<List<Movie>>>
}