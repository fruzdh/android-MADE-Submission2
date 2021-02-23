package com.example.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movieFavorite = MutableLiveData<List<Movie>>()
    val movieFavorite: LiveData<List<Movie>>
        get() = _movieFavorite

    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            movieUseCase.getMovieFavorite().collect {
                _movieFavorite.value = it
            }
        }
    }
}