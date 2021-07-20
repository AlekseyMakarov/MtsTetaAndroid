package com.example.mtstetaandroid

import com.example.mtstetaandroid.data.features.movies.MoviesDataSource

class MoviesModel(
	private val moviesDataSource: MoviesDataSource
) {
	
	fun getMovies() = moviesDataSource.getMovies()
}