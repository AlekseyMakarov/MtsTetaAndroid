package com.example.mtstetaandroid.ui.movieDetails

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import coil.load
import com.example.mtstetaandroid.R
import com.example.mtstetaandroid.data.dto.MovieDto
import com.example.mtstetaandroid.dpToPx

class DetailsFragment : Fragment() {

    private var scaledRatingDrawable: Drawable? = null


    private fun createScaledRatingDrawable(context: Context): Drawable {

        val starEmptyBitmap = AppCompatResources.getDrawable(context, R.drawable.ic_star_empty)
            ?.toBitmap(context.dpToPx(15F), context.dpToPx(15F))
        val starBitmap = AppCompatResources.getDrawable(context, R.drawable.ic_star)
            ?.toBitmap(context.dpToPx(15F), context.dpToPx(15F))
        val newStarEmptyDrawable = BitmapDrawable(context.resources, starEmptyBitmap)
        val newStarDrawable = BitmapDrawable(context.resources, starBitmap)
        val finalDrawable = LayerDrawable(
            arrayOf(
                newStarEmptyDrawable,
                newStarEmptyDrawable,
                newStarDrawable,
            )
        )
        finalDrawable.setId(0, android.R.id.background)
        finalDrawable.setId(1, android.R.id.secondaryProgress)
        finalDrawable.setId(2, android.R.id.progress)
        return finalDrawable
    }

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
        if (scaledRatingDrawable == null) {
            scaledRatingDrawable = createScaledRatingDrawable(root.context)
        }


        val imageView = root.findViewById<ImageView>(R.id.imageView)?.apply {
            this.load(arguments?.getString("movie_image_url")) {
                allowHardware(false)
            }
        }
        val textViewAgeRestriction =
            root.findViewById<TextView>(R.id.textViewMovieAgeRestrictions)?.apply {
                text = arguments?.getInt("movie_age_restriction").toString() + "+"
            }
        val textViewDescription = root.findViewById<TextView>(R.id.textView)?.apply {
            text = arguments?.getString("movie_description")
        }
        val textViewTitle = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
            text = arguments?.getString("movie_title")
        }
        val ratingBar = root.findViewById<RatingBar>(R.id.ratingBarMovieRatingDetails).apply {
            setProgressDrawableTiled(scaledRatingDrawable)
            rating = arguments?.getInt("movie_rate_score")!!.toFloat()
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
        fun newInstance(movie: MovieDto): DetailsFragment {
            val args = Bundle()
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