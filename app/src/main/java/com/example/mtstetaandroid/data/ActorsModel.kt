package com.example.mtstetaandroid.data

import com.example.mtstetaandroid.data.features.movies.ActorsDataSource

class ActorsModel(
	private val genresDataSource: ActorsDataSource
) {
	
	fun getActors() = genresDataSource.getActors()
}