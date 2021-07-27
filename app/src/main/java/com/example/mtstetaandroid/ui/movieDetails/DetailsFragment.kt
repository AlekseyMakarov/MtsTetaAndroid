package com.example.mtstetaandroid.ui.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.mtstetaandroid.R
import com.example.mtstetaandroid.data.dto.MovieDto
import com.example.mtstetaandroid.ui.dashboard.DashboardViewModel

class DetailsFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        dashboardViewModel =
//                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        val imageView = root.findViewById<ImageView>(R.id.imageView)?.apply {
           this.load(arguments?.getString("movie_image_url"))
        }
        val textViewAgeRestriction = root.findViewById<TextView>(R.id.textViewMovieAgeRestrictions)?.apply {
            text = arguments?.getInt("movie_age_restriction").toString() + "+"
        }
        val textViewDescription = root.findViewById<TextView>(R.id.textView)?.apply {
            text = arguments?.getString("movie_description")
        }
        val textViewTitle = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
            text = arguments?.getString("movie_title")
        }
//        val textViewRateScore = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
//            text = arguments?.getString("movie_title")
//        }
//        val textViewTitle = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
//            text = arguments?.getString("movie_title")
//        }
//        val textViewTitle = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
//            text = arguments?.getString("movie_title")
//        }


        return root
    }

    companion object {
        fun newInstance(message: String, movie: MovieDto): DetailsFragment {
            val args = Bundle()
            args.putString("MTS", message)
            args.putString("movie_title", movie.title)
            args.putString("movie_description", movie.description)
            args.putString("movie_image_url", movie.imageUrl)
            args.putInt("movie_age_restriction", movie.ageRestriction)
            args.putInt("movie_rate_score", movie.rateScore)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}