package ru.mts.teta.summer.android.homework.list.data.features.movies

import com.example.mtstetaandroid.data.features.movies.GenresDataSource

class GenresDataSourceImpl : GenresDataSource {
	override fun getGenres() = listOf(
		"боевик",
		"драма",
		"мелодрама",
		"ужасы",
		"триллеры",
		"комедии",
		"хаус",
		"про войну",
		"документальные"
	)
}