package com.example.mtstetaandroid.data.features.movies

interface ActorsDataSource {
    fun getActors(): List<Pair<String, Int>>
}