package com.example.mtstetaandroid

import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.data.GenresModel
import com.example.mtstetaandroid.data.dto.MovieDto
import ru.mts.teta.summer.android.homework.list.data.features.movies.GenresDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MainActivity : AppCompatActivity() {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list)

        val recycler = findViewById<RecyclerView>(R.id.recyclerViewGenres)
        initGenresSource()
        val genres = genresModel.getGenres()
        val adapter = GenreAdapter()
        adapter.setData(genres)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        val recyclerMovie = findViewById<RecyclerView>(R.id.recyclerViewMovies)
        initDataSource()

        val movies = moviesModel.getMovies()
        val adapterMovie = MovieAdapter()
        adapterMovie.setOnClickListener(::showToast)
        adapterMovie.setData(movies)
        recyclerMovie.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.set(
                    if (itemPosition % 2 == 0) dpToPx(20f) else dpToPx(10f),
                    if (itemPosition < 2) dpToPx(0f) else dpToPx(50f),
                    if (itemPosition % 2 == 1) dpToPx(20f) else dpToPx(10f),
                    when (parent.adapter!!.itemCount % 2) {
                        0 -> if (itemPosition == parent.adapter!!.itemCount - 1 || itemPosition == parent.adapter!!.itemCount - 2) dpToPx(
                            16f
                        ) else 0
                        1 -> if (itemPosition == parent.adapter!!.itemCount - 1) dpToPx(16f) else 0
                        else -> 0
                    }
                )
            }
        })
        recyclerMovie.adapter = adapterMovie
        recyclerMovie.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

    }

    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
    }
    private fun initGenresSource() {
        genresModel = GenresModel(GenresDataSourceImpl())
    }


    fun showToast(message: String?) {
        when {
            message.isNullOrEmpty() -> {
                showToast(getString(R.string.main_empty_message))
            }
            else -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
