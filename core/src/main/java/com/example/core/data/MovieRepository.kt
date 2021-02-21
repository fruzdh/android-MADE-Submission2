package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.MovieDetailResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : IMovieRepository {
    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: MovieDetail?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieFavorite(): Flow<List<MovieDetail>> {
        TODO("Not yet implemented")
    }

    override fun updateMovieDetail(movieDetail: MovieDetail, newState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieRecommendations(id: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieSearch(query: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieSimilar(id: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                TODO("Not yet implemented")
            }
        }.asFlow()
}