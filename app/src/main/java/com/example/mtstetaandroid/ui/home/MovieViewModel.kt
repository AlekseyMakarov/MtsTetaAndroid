package com.example.mtstetaandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtstetaandroid.MoviesModel
import com.example.mtstetaandroid.data.dto.MovieDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MovieViewModel() : ViewModel() {

    var dataProvider: DataProvider = MoviesModel(MoviesDataSourceImpl())

    private val _movies = MutableLiveData<MoviesState>()
    val movies: LiveData<MoviesState>
        get() = _movies

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _movies.postValue(MoviesState.LoadingState)

            try {
                lateinit var movies: List<MovieDto>
                withContext(Dispatchers.IO) {
                    movies = dataProvider.getMovies()
                }
                _movies.setValue(MoviesState.SuccessState(movies))

            } catch (t: Throwable) {
                _movies.postValue(MoviesState.ErrorState)
            }
        }
    }

}

sealed class MoviesState {
    object LoadingState : MoviesState()
    class SuccessState(val movies: List<MovieDto>) : MoviesState()
    object ErrorState : MoviesState()
}

interface DataProvider {
    suspend fun getMovies(): List<MovieDto>
}