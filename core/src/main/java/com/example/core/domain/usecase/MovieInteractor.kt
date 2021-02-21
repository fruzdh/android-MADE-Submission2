package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> = movieRepository.getMovieDetail(id)

    override fun getMovieFavorite(): Flow<List<MovieDetail>> = movieRepository.getMovieFavorite()

    override fun updateMovieDetail(movieDetail: MovieDetail, newState: Boolean) = movieRepository.updateMovieDetail(movieDetail, newState)

    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> = movieRepository.getMovieNowPlaying()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> = movieRepository.getMoviePopular()

    override fun getMovieRecommendations(id: Int): Flow<Resource<List<Movie>>> = movieRepository.getMovieRecommendations(id)

    override fun getMovieSearch(query: String): Flow<Resource<List<Movie>>> = movieRepository.getMovieSearch(query)

    override fun getMovieSimilar(id: Int): Flow<Resource<List<Movie>>> = movieRepository.getMovieSimilar(id)

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> = movieRepository.getMovieTopRated()

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> = movieRepository.getMovieUpcoming()
}