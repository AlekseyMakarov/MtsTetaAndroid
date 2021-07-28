package com.example.mtstetaandroid

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mtstetaandroid.data.dto.MovieDto


class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movies: List<MovieDto>? = null
    private var scaledRatingDrawable: Drawable? = null
    private var callback: ((MovieDto?) -> Unit)? = null

    private fun createScaledRatingDrawable(context: Context): Drawable {
        val starEmptyBitmap = getDrawable(context, R.drawable.ic_star_empty)?.toBitmap()
        val starBitmap = getDrawable(context, R.drawable.ic_star)?.toBitmap()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        if (scaledRatingDrawable == null) {
            scaledRatingDrawable = createScaledRatingDrawable(parent.context)
        }

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            ).apply {
                this.findViewById<RatingBar>(R.id.ratingBarMovieRating).apply {
                    setProgressDrawableTiled(scaledRatingDrawable)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, callback) }
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    fun setData(movies: List<MovieDto>): Unit {
        this.movies = movies
    }

    fun setOnClickListener(callback: (MovieDto?) -> Unit): Unit {
        this.callback = callback
    }

    private fun getItem(position: Int): MovieDto? = movies?.get(position)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImage: ImageView = itemView.findViewById(R.id.roundedImageViewMovie)
        private val movieTitle: TextView = itemView.findViewById(R.id.textViewMovieTitle)
        private val movieDescription: TextView =
            itemView.findViewById(R.id.textViewMovieDescription)
        private val movieRating: RatingBar = itemView.findViewById(R.id.ratingBarMovieRating)
        private val movieAgeRestriction: TextView =
            itemView.findViewById(R.id.textViewMovieAgeRestrictions)

        fun bind(Movie: MovieDto, callback: ((MovieDto?) -> Unit)?) {
            movieImage.load(Movie.imageUrl) {
                allowHardware(false)
            }
            movieTitle.text = Movie.title
            movieDescription.text = Movie.description
            movieRating.rating = Movie.rateScore.toFloat()
            movieAgeRestriction.text = Movie.ageRestriction.toString() + "+"
            callback?.let { itemView.setOnClickListener { callback(Movie) } }
        }
    }
}