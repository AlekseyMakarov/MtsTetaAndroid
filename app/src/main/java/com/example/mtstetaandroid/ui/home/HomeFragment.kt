package com.example.mtstetaandroid.ui.home

import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


object HomeFragmentConstants {
    const val FRAGMENT_NAME = "HomeFragment"
}

class HomeFragment : Fragment() {

    val viewModel by activityViewModels<MovieViewModel>()
    private lateinit var genresModel: GenresModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        val v: SwipeRefreshLayout =
            root.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayoutHome)
        v.setOnRefreshListener {
            viewModel.refresh()
        }
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewGenres)
        initGenresSource()
        val genres = genresModel.getGenres()
        val adapter = GenreAdapter()
        adapter.setData(genres)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(root.context, RecyclerView.HORIZONTAL, false)
        val recyclerMovie = root.findViewById<RecyclerView>(R.id.recyclerViewMovies)
        val adapterMovie = MovieAdapter()
        adapterMovie.setOnClickListener(::showDetails)

        recyclerMovie.addItemDecoration(RecyclerDecoration)
        recyclerMovie.adapter = adapterMovie
        recyclerMovie.layoutManager =
            GridLayoutManager(root.context, 2, RecyclerView.VERTICAL, false)

        viewModel.movies.observe(viewLifecycleOwner) { state: MoviesState ->
            when (state) {
                MoviesState.ErrorState -> {
                    v.isRefreshing = false
                    Log.e(HomeFragmentConstants.FRAGMENT_NAME, "error occured while loading")
                }
                MoviesState.LoadingState -> {
                    v.isRefreshing = true
                    Log.i(HomeFragmentConstants.FRAGMENT_NAME, "loading")
                }
                is MoviesState.SuccessState -> {
                    v.isRefreshing = false
                    adapterMovie.setData(state.movies)
                }
            }
        }
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




