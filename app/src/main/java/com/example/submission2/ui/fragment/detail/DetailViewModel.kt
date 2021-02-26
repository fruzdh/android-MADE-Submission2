package com.example.submission2.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movieDetail = MutableLiveData<Resource<MovieDetail>>()
    val movieDetail: LiveData<Resource<MovieDetail>>
        get() = _movieDetail

    private val _movieRecommendations = MutableLiveData<Resource<List<Movie>>>()
    val movieRecommendations: LiveData<Resource<List<Movie>>>
        get() = _movieRecommendations

    private val _movieSimilar = MutableLiveData<Resource<List<Movie>>>()
    val movieSimilar: LiveData<Resource<List<Movie>>>
        get() = _movieSimilar

    private val id = MutableLiveData<Int>()

    fun setId(id: Int) {
        this.id.value = id
    }

    fun updateMovieDetail(movie: MovieDetail, newState: Boolean) =
        movieUseCase.updateMovieDetail(movie, newState)

    fun setItem() {
        if (id.value != null) {
            viewModelScope.launch {
                movieUseCase.getMovieDetail(id.value!!).collect {
                    _movieDetail.value = it
                }
            }
            viewModelScope.launch {
                movieUseCase.getMovieRecommendations(id.value!!).collect {
                    _movieRecommendations.value = it
                }
            }
            viewModelScope.launch {
                movieUseCase.getMovieSimilar(id.value!!).collect {
                    _movieSimilar.value = it
                }
            }
        }
    }
}