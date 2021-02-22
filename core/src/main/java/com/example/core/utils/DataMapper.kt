package com.example.core.utils

import com.example.core.data.source.local.entity.*
import com.example.core.data.source.remote.response.MovieDetailResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail

object DataMapper {
    fun mapMovieDetailResponseToMovieDetailEntity(input: MovieDetailResponse) = MovieDetailEntity(
            id = input.id,
            title = input.title,
            tagline = input.tagline,
            release_date = input.release_date,
            genres = input.genres.joinToString(", ") { it.name },
            runtime = input.runtime,
            overview = input.overview,
            poster_path = input.poster_path
    )

    fun mapMovieResponseToMovieNowPlayingEntity(input: List<MovieResponse>): List<MovieNowPlayingEntity> =
        input.map {
            MovieNowPlayingEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMoviePopularEntity(input: List<MovieResponse>): List<MoviePopularEntity> =
        input.map {
            MoviePopularEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMovieRecommendationsEntity(id: Int, input: List<MovieResponse>): List<MovieRecommendationsEntity> =
        input.map {
            MovieRecommendationsEntity(
                    movieId = id,
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMovieSearchEntity(input: List<MovieResponse>): List<MovieSearchEntity> =
        input.map {
            MovieSearchEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMovieSimilarEntity(id: Int, input: List<MovieResponse>): List<MovieSimilarEntity> =
        input.map {
            MovieSimilarEntity(
                    movieId = id,
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMovieTopRatedEntity(input: List<MovieResponse>): List<MovieTopRatedEntity> =
        input.map {
            MovieTopRatedEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieResponseToMovieUpcomingEntity(input: List<MovieResponse>): List<MovieUpcomingEntity> =
        input.map {
            MovieUpcomingEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieDetailEntityToMovieDetail(input: MovieDetailEntity) = MovieDetail(
            id = input.id,
            title = input.title,
            tagline = input.tagline,
            release_date = input.release_date,
            genres = input.genres,
            runtime = input.runtime,
            overview = input.overview,
            poster_path = input.poster_path,
            favorite = input.favorite
    )

    fun mapMovieDetailEntityToMovie(input: List<MovieDetailEntity>): List<Movie> =
        input.map {
            Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster_path = it.poster_path
            )
        }

    fun mapMovieNowPlayingEntityToMovie(input: List<MovieNowPlayingEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMoviePopularEntityToMovie(input: List<MoviePopularEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieRecommendationsEntityToMovie(input: List<MovieRecommendationsEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieSearchEntityToMovie(input: List<MovieSearchEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieSimilarEntityToMovie(input: List<MovieSimilarEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieTopRatedEntityToMovie(input: List<MovieTopRatedEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieUpcomingEntityToMovie(input: List<MovieUpcomingEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        poster_path = it.poster_path
                )
            }

    fun mapMovieDetailToMovieDetailEntity(input: MovieDetail) = MovieDetailEntity(
            id = input.id,
            title = input.title,
            tagline = input.tagline,
            release_date = input.release_date,
            genres = input.genres,
            runtime = input.runtime,
            overview = input.overview,
            poster_path = input.poster_path,
            favorite = input.favorite
    )
}