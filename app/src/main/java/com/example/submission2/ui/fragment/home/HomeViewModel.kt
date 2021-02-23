package com.example.submission2.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movieSearch = MutableLiveData<Resource<List<Movie>>>()
    val movieSearch: LiveData<Resource<List<Movie>>>
        get() = _movieSearch

    private val _moviePopular = MutableLiveData<Resource<List<Movie>>>()
    val moviePopular: LiveData<Resource<List<Movie>>>
        get() = _moviePopular

    private val _movieNowPlaying = MutableLiveData<Resource<List<Movie>>>()
    val movieNowPlaying: LiveData<Resource<List<Movie>>>
        get() = _movieNowPlaying

    private val _movieTopRated = MutableLiveData<Resource<List<Movie>>>()
    val movieTopRated: LiveData<Resource<List<Movie>>>
        get() = _movieTopRated

    private val _movieUpcoming = MutableLiveData<Resource<List<Movie>>>()
    val movieUpcoming: LiveData<Resource<List<Movie>>>
        get() = _movieUpcoming

    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            movieUseCase.getMoviePopular().collect {
                _moviePopular.value = it
            }
        }
        viewModelScope.launch {
            movieUseCase.getMovieNowPlaying().collect {
                _movieNowPlaying.value = it
            }
        }
        viewModelScope.launch {
            movieUseCase.getMovieTopRated().collect {
                _movieTopRated.value = it
            }
        }
        viewModelScope.launch {
            movieUseCase.getMovieUpcoming().collect {
                _movieUpcoming.value = it
            }
        }
    }

    fun searchItem(query: String) {
        viewModelScope.launch {
            movieUseCase.getMovieSearch(query).collect {
                _movieSearch.value = it
            }
        }
    }
}