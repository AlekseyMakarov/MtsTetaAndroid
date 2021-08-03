package com.example.mtstetaandroid.data

import com.example.mtstetaandroid.data.features.movies.GenresDataSource

class GenresModel(
	private val genresDataSource: GenresDataSource
) {
	
	fun getGenres() = genresDataSource.getGenres()
}