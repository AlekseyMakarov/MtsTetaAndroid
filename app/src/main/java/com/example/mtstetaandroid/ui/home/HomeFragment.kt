package com.example.mtstetaandroid.ui.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.*
import com.example.mtstetaandroid.data.GenresModel
//import com.example.mtstetaandroid.ui.home.HomeViewModel
import com.google.android.material.internal.ViewUtils.dpToPx
import ru.mts.teta.summer.android.homework.list.data.features.movies.GenresDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
import com.example.mtstetaandroid.dpToPx
import com.example.mtstetaandroid.ui.movieDetails.DetailsFragment

class HomeFragment : Fragment() {

    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
//    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })








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
        adapterMovie.setOnClickListener(::showToast)
        adapterMovie.setData(movies)
        recyclerMovie.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.set(
                    if (itemPosition % 2 == 0) root.dpToPx(20f) else root.dpToPx(10f),
                    if (itemPosition < 2) root.dpToPx(0f) else root.dpToPx(50f),
                    if (itemPosition % 2 == 1) root.dpToPx(20f) else root.dpToPx(10f),
                    when (parent.adapter!!.itemCount % 2) {
                        0 -> if (itemPosition == parent.adapter!!.itemCount - 1 || itemPosition == parent.adapter!!.itemCount - 2) root.dpToPx(
                            16f
                        ) else 0
                        1 -> if (itemPosition == parent.adapter!!.itemCount - 1) root.dpToPx(16f) else 0
                        else -> 0
                    }
                )
            }
        })
        recyclerMovie.adapter = adapterMovie
        recyclerMovie.layoutManager = GridLayoutManager(root.context, 2, RecyclerView.VERTICAL, false)









        return root
    }







    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
    }
    private fun initGenresSource() {
        genresModel = GenresModel(GenresDataSourceImpl())
    }


    fun showToast(message: String?) {
        val detailsFragment = DetailsFragment()
        childFragmentManager.beginTransaction().add(R.id.fragment_container_details, detailsFragment).addToBackStack(null).commit()
        when {
            message.isNullOrEmpty() -> {
                showToast(getString(R.string.main_empty_message))
            }
            else -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }





}




