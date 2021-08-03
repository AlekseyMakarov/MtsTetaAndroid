package com.example.mtstetaandroid.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.*
import com.example.mtstetaandroid.data.GenresModel
import com.example.mtstetaandroid.data.dto.MovieDto
import ru.mts.teta.summer.android.homework.list.data.features.movies.GenresDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
import com.example.mtstetaandroid.extensions.dpToPx
import com.example.mtstetaandroid.ui.movieDetails.DetailsFragment

class HomeFragment : Fragment() {

    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewGenres)
        initGenresSource()
        val genres = genresModel.getGenres()
        val adapter = GenreAdapter()
        adapter.setData(genres)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(root.context, RecyclerView.HORIZONTAL, false)
        val recyclerMovie = root.findViewById<RecyclerView>(R.id.recyclerViewMovies)
        initDataSource()
        val movies = moviesModel.getMovies()
        val adapterMovie = MovieAdapter()
        adapterMovie.setOnClickListener(::showDetails)
        adapterMovie.setData(movies)
        recyclerMovie.addItemDecoration(RecyclerDecoration)
        recyclerMovie.adapter = adapterMovie
        recyclerMovie.layoutManager =
            GridLayoutManager(root.context, 2, RecyclerView.VERTICAL, false)
        return root
    }


    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
    }

    private fun initGenresSource() {
        genresModel = GenresModel(GenresDataSourceImpl())
    }


    fun showDetails(movie: MovieDto) {
        childFragmentManager.beginTransaction().add(
            R.id.fragment_container_details,
            DetailsFragment.newInstance(movie)
        ).addToBackStack(null).commit()
    }
}




