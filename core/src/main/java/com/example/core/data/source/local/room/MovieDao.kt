package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieDetailEntities WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM MovieDetailEntities WHERE favorite = 1")
    fun getMovieFavorite(): Flow<List<MovieDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity)

    @Update
    fun updateMovieDetail(movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM MovieNowPlayingEntities")
    fun getMovieNowPlaying(): Flow<List<MovieNowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movieNowPlayingEntity: List<MovieNowPlayingEntity>)

    @Query("SELECT * FROM MoviePopularEntities")
    fun getMoviePopular(): Flow<List<MoviePopularEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(moviePopularEntity: List<MoviePopularEntity>)

    @Query("SELECT * FROM MovieRecommendationsEntities WHERE movieId = :id")
    fun getMovieRecommendations(id: Int): Flow<List<MovieRecommendationsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieRecommendations(movieRecommendationsEntity: List<MovieRecommendationsEntity>)

    @Query(
        "SELECT * FROM MovieNowPlayingEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT * FROM MoviePopularEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT id, title, poster_path, overview FROM MovieRecommendationsEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT * FROM MovieSearchEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT id, title, poster_path, overview FROM MovieSimilarEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT * FROM MovieTopRatedEntities WHERE title LIKE :query " +
                "UNION " +
                "SELECT * FROM MovieUpcomingEntities WHERE title LIKE :query"
    )
    fun getMovieSearch(query: String): Flow<List<MovieSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSearch(movieSearchEntity: List<MovieSearchEntity>)

    @Query("SELECT * FROM MovieSimilarEntities WHERE movieId = :id")
    fun getMovieSimilar(id: Int): Flow<List<MovieSimilarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSimilar(movieSimilarEntity: List<MovieSimilarEntity>)

    @Query("SELECT * FROM MovieTopRatedEntities")
    fun getMovieTopRated(): Flow<List<MovieTopRatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTopRated(movieTopRatedEntity: List<MovieTopRatedEntity>)

    @Query("SELECT * FROM MovieUpcomingEntities")
    fun getMovieUpcoming(): Flow<List<MovieUpcomingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieUpcoming(movieUpcomingEntity: List<MovieUpcomingEntity>)
}