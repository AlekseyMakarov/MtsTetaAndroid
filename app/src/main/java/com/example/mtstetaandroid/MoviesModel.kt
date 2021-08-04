package com.example.mtstetaandroid

import com.example.mtstetaandroid.data.dto.MovieDto
import com.example.mtstetaandroid.data.features.movies.MoviesDataSource
import com.example.mtstetaandroid.ui.home.DataProvider
import kotlinx.coroutines.delay

class MoviesModel(
	private val moviesDataSource: MoviesDataSource
): DataProvider {
	var count = 0

	override suspend fun getMovies(): List<MovieDto>{
		when (count){
			0 -> {
				count++
				delay(1000)
				return moviesDataSource.getMovies().slice(0..2)
			}
			1 -> {
				count++
				delay(1000)
				return moviesDataSource.getMovies().slice(0..5)
			}
			2 -> {
				count++
				delay(1000)
				return moviesDataSource.getMovies().slice(0..7)
			}
			else -> {
				delay(1000)
				return moviesDataSource.getMovies().slice(0..7)
			}
		}
	}
}