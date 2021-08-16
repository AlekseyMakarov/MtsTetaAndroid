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
import com.example.mtstetaandroid.ui.movieDetails.DetailsFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mtstetaandroid.ui.movieDetails.BundleKeysConstants


object HomeFragmentConstants {
    const val FRAGMENT_NAME = "HomeFragment"
}

class HomeFragment : Fragment() {

    val viewModel by activityViewModels<MovieViewModel>()
    private lateinit var genresModel: GenresModel
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var movieAdapter: MovieAdapter

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
        initGenresSource()
        initAdapters()
        initRecyclers(root, v)
    }

    private fun initGenresSource() {
        genresModel = GenresModel(GenresDataSourceImpl())
    }

    private fun initAdapters(){
        val genres = genresModel.getGenres()
        genreAdapter = GenreAdapter()
        genreAdapter.setData(genres)
        movieAdapter = MovieAdapter()
        movieAdapter.setOnClickListener(::showDetails)
    }

    private fun  initRecyclers(root: View, v: SwipeRefreshLayout){
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewGenres)
        initGenresSource()
        recycler.adapter = genreAdapter
        recycler.layoutManager = LinearLayoutManager(root.context, RecyclerView.HORIZONTAL, false)
        val recyclerMovie = root.findViewById<RecyclerView>(R.id.recyclerViewMovies)
        recyclerMovie.addItemDecoration(RecyclerDecoration)
        recyclerMovie.adapter = movieAdapter
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
                    movieAdapter.setData(state.movies)
                }
            }
        }
    }


    fun showDetails(movie: MovieDto) {
        val args = Bundle()
        args.putString(BundleKeysConstants.MOVIE_TITLE, movie.title)
        args.putString(BundleKeysConstants.MOVIE_DESCRIPTION, movie.description)
        args.putString(BundleKeysConstants.MOVIE_IMAGE_URL, movie.imageUrl)
        args.putInt(BundleKeysConstants.MOVIE_AGE_RESTRICTION, movie.ageRestriction)
        args.putInt(BundleKeysConstants.MOVIE_RATE_SCORE, movie.rateScore)
        findNavController().navigate(R.id.action_home_fragment_to_details_fragment, args)
    }
}




