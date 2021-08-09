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
import com.example.mtstetaandroid.extensions.dpToPx

object BundleKeysConstants {
    const val MOVIE_TITLE = "movie_title"
    const val MOVIE_DESCRIPTION = "movie_description"
    const val MOVIE_IMAGE_URL = "movie_image_url"
    const val MOVIE_AGE_RESTRICTION = "movie_age_restriction"
    const val MOVIE_RATE_SCORE = "movie_rate_score"
}


class DetailsFragment : Fragment() {

    private var scaledRatingDrawable: Drawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        if (scaledRatingDrawable == null) {
            scaledRatingDrawable = createScaledRatingDrawable(root.context)
        }
        val imageView = root.findViewById<ImageView>(R.id.imageView)?.apply {
            this.load(arguments?.getString(BundleKeysConstants.MOVIE_IMAGE_URL)) {
                allowHardware(false)
            }
        }
        val textViewAgeRestriction =
            root.findViewById<TextView>(R.id.textViewMovieAgeRestrictions)?.apply {
                text = arguments?.getInt(BundleKeysConstants.MOVIE_AGE_RESTRICTION).toString() + "+"
            }
        val textViewDescription = root.findViewById<TextView>(R.id.textView)?.apply {
            text = arguments?.getString(BundleKeysConstants.MOVIE_DESCRIPTION)
        }
        val textViewTitle = root.findViewById<TextView>(R.id.textViewFilmName)?.apply {
            text = arguments?.getString(BundleKeysConstants.MOVIE_TITLE)
        }
        val ratingBar = root.findViewById<RatingBar>(R.id.ratingBarMovieRatingDetails).apply {
            setProgressDrawableTiled(scaledRatingDrawable)
            rating = arguments?.getInt(BundleKeysConstants.MOVIE_RATE_SCORE)?.toFloat() ?: 0F
        }
    }

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

    companion object {
        fun newInstance(movie: MovieDto): DetailsFragment {
            val args = Bundle()
            args.putString(BundleKeysConstants.MOVIE_TITLE, movie.title)
            args.putString(BundleKeysConstants.MOVIE_DESCRIPTION, movie.description)
            args.putString(BundleKeysConstants.MOVIE_IMAGE_URL, movie.imageUrl)
            args.putInt(BundleKeysConstants.MOVIE_AGE_RESTRICTION, movie.ageRestriction)
            args.putInt(BundleKeysConstants.MOVIE_RATE_SCORE, movie.rateScore)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}