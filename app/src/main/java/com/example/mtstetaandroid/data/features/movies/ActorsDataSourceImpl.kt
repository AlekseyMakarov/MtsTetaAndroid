package com.example.mtstetaandroid.data.features.movies

import com.example.mtstetaandroid.R

class ActorsDataSourceImpl : ActorsDataSource {
	override fun getActors() = listOf(
		Pair("Джейсон Стейтем", R.drawable.image_jason_statham_actor),
		Pair("Холт Маккэланни", R.drawable.image_holt_mccallany_actor),
		Pair("Джош Харнетт", R.drawable.image_josh_hartnett_actor)
	)
}