package com.example.core.data.source.remote

import android.content.res.Resources
import com.example.core.BuildConfig
import com.example.core.R
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MovieDetailResponse
import com.example.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovieNowPlaying(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieNowPlaying(BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviePopular(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviePopular(BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieTopRated(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieTopRated(BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieUpcoming(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieUpcoming(BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(id: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(id, BuildConfig.apiKey)
                emit(ApiResponse.Success(response))
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieRecommendations(id: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieRecommendations(id, BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieSimilar(id: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieSimilar(id, BuildConfig.apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(Resources.getSystem().getString(R.string.remote_error)))
            }
        }.flowOn(Dispatchers.IO)
    }
}