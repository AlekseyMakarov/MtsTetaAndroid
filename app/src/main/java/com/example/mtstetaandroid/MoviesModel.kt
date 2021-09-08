package com.example.mtstetaandroid

import com.example.mtstetaandroid.data.MovieEntity
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
				val db= MyApp.getInstance()?.database
				val movieDao = db?.movieDao()
				val movies_list = moviesDataSource.getMovies()
				val movie_entity_list: MutableList<MovieEntity> = mutableListOf<MovieEntity>()
				for(movie in movies_list){
					movie_entity_list.add(MovieEntity(movie.title, movie.description, movie.rateScore, movie.ageRestriction, movie.imageUrl))
				}
				movieDao?.insertAll(movie_entity_list)
				return moviesDataSource.getMovies().slice(0..7)
			}
		}
	}
}