package com.example.mtstetaandroid.data.features.movies

import com.example.mtstetaandroid.data.dto.MovieDto

interface MoviesDataSource {
	fun getMovies(): List<MovieDto>
}