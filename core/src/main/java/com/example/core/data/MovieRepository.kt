package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.MovieDetailResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                return localDataSource.getMovieDetail(id).map {
                    try {
                        DataMapper.mapMovieDetailEntityToMovieDetail(it)
                    } catch (e: NullPointerException) {
                        MovieDetail(-1, "", null, "", "", null, null, null, 0.0f, false)
                    }
                }
            }

            override fun shouldFetch(
                data: MovieDetail?,
                remote: ApiResponse<MovieDetailResponse>
            ): Boolean {
                return if (data?.id == -1) {
                    true
                } else {
                    when (remote) {
                        is ApiResponse.Success -> {
                            val entity =
                                DataMapper.mapMovieDetailResponseToMovieDetailEntity(remote.data)
                            entity.favorite = data!!.favorite
                            val domain = DataMapper.mapMovieDetailEntityToMovieDetail(entity)
                            domain != data
                        }
                        is ApiResponse.Empty -> {
                            data == null
                        }
                        is ApiResponse.Error -> {
                            data == null
                        }
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: MovieDetailResponse) =
                localDataSource.insertMovieDetail(
                    DataMapper.mapMovieDetailResponseToMovieDetailEntity(
                        data
                    )
                )
        }.asFlow()

    override fun getMovieFavorite(): Flow<List<Movie>> =
        localDataSource.getMovieFavorite().map {
            DataMapper.mapMovieDetailEntityToMovie(it)
        }

    override fun updateMovieDetail(movieDetail: MovieDetail, newState: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateMovieDetail(
                DataMapper.mapMovieDetailToMovieDetailEntity(
                    movieDetail
                ), newState
            )
        }

    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieNowPlaying().map {
                    DataMapper.mapMovieNowPlayingEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity = DataMapper.mapMovieResponseToMovieNowPlayingEntity(remote.data)
                        val domain = DataMapper.mapMovieNowPlayingEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieNowPlaying()

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieNowPlaying(
                    DataMapper.mapMovieResponseToMovieNowPlayingEntity(
                        data
                    )
                )
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMoviePopular().map {
                    DataMapper.mapMoviePopularEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity = DataMapper.mapMovieResponseToMoviePopularEntity(remote.data)
                        val domain = DataMapper.mapMoviePopularEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMoviePopular()

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMoviePopular(
                    DataMapper.mapMovieResponseToMoviePopularEntity(
                        data
                    )
                )
        }.asFlow()

    override fun getMovieRecommendations(id: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieRecommendations(id).map {
                    DataMapper.mapMovieRecommendationsEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity =
                            DataMapper.mapMovieResponseToMovieRecommendationsEntity(id, remote.data)
                        val domain = DataMapper.mapMovieRecommendationsEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieRecommendations(id)

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieRecommendations(
                    DataMapper.mapMovieResponseToMovieRecommendationsEntity(
                        id,
                        data
                    )
                )
        }.asFlow()

    override fun getMovieSearch(query: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieSearch(query).map {
                    DataMapper.mapMovieSearchEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity = DataMapper.mapMovieResponseToMovieSearchEntity(remote.data)
                        val domain = DataMapper.mapMovieSearchEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieSearch(query)

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieSearch(
                    DataMapper.mapMovieResponseToMovieSearchEntity(
                        data
                    )
                )
        }.asFlow()

    override fun getMovieSimilar(id: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieSimilar(id).map {
                    DataMapper.mapMovieSimilarEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity =
                            DataMapper.mapMovieResponseToMovieSimilarEntity(id, remote.data)
                        val domain = DataMapper.mapMovieSimilarEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieSimilar(id)

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieSimilar(
                    DataMapper.mapMovieResponseToMovieSimilarEntity(
                        id,
                        data
                    )
                )
        }.asFlow()

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieTopRated().map {
                    DataMapper.mapMovieTopRatedEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity = DataMapper.mapMovieResponseToMovieTopRatedEntity(remote.data)
                        val domain = DataMapper.mapMovieTopRatedEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieTopRated()

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieTopRated(
                    DataMapper.mapMovieResponseToMovieTopRatedEntity(
                        data
                    )
                )
        }.asFlow()

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovieUpcoming().map {
                    DataMapper.mapMovieUpcomingEntityToMovie(it)
                }

            override fun shouldFetch(
                data: List<Movie>?,
                remote: ApiResponse<List<MovieResponse>>
            ): Boolean {
                return when (remote) {
                    is ApiResponse.Success -> {
                        val entity = DataMapper.mapMovieResponseToMovieUpcomingEntity(remote.data)
                        val domain = DataMapper.mapMovieUpcomingEntityToMovie(entity)
                        domain != data
                    }
                    is ApiResponse.Empty -> {
                        data == null
                    }
                    is ApiResponse.Error -> {
                        data == null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieUpcoming()

            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovieUpcoming(
                    DataMapper.mapMovieResponseToMovieUpcomingEntity(
                        data
                    )
                )
        }.asFlow()
}