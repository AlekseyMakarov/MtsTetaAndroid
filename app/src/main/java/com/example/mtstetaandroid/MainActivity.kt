package com.example.mtstetaandroid

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import androidx.core.graphics.drawable.toBitmap

class MainActivity : AppCompatActivity() {
	private lateinit var moviesModel: MoviesModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.movie_item)
		val rating = findViewById<RatingBar>(R.id.rb_rating)
		val starEmptyBitmap = getDrawable(R.drawable.ic_star_empty)?.toBitmap(20, 20)
		val starBitmap = getDrawable(R.drawable.ic_star)?.toBitmap(20, 20)
		val newStarEmptyDrawable = BitmapDrawable(resources, starEmptyBitmap)
		val newStarDrawable = BitmapDrawable(resources, starBitmap)

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
		rating.setProgressDrawableTiled(finalDrawable)
//		val recycler = findViewById<RecyclerView>(R.id.recyclerViewGenres)
//		val genres = listOf("боевик", "драма", "мелодрама", "ужасы", "триллеры", "комедии", "хаус", "про войну", "документальные")
//		val adapter = GenreAdapter()
//		adapter.setData(genres)
//		recycler.adapter = adapter
//		recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
		
//		initDataSource()
//		setupViews()
//		setupListeners()
	}}
	
//	private fun initDataSource() {
//		moviesModel = MoviesModel(MoviesDataSourceImpl())
//	}
//
//	private fun setupViews() {
//		getMovieAt(MOVIES_INITIAL_POSITION)?.let {
//			// https://coil-kt.github.io/coil/
//			findViewById<ImageView>(R.id.ivHeader)?.load(it.imageUrl)
//
//			findViewById<TextView>(R.id.tvTitle)?.text = it.title
//			findViewById<TextView>(R.id.tvDescription)?.text = it.description
//			findViewById<TextView>(R.id.tvRating)?.text =
//				getString(R.string.main_rating_text, it.rateScore)
//			findViewById<TextView>(R.id.tvAgeRestriction)?.text =
//				getString(R.string.main_age_restriction_text, it.ageRestriction)
//		}
//	}
//
//	private fun setupListeners() {
//		findViewById<View>(R.id.root)?.setOnClickListener {
//			showToast(getToastMessage())
//		}
//	}
//
//	private fun getMovieAt(position: Int): MovieDto? {
//		val movies = moviesModel.getMovies()
//		return when {
//			movies.isEmpty() -> null
//			position >= movies.size -> null
//			else -> movies[position]
//		}
//	}
//
//	private fun getToastMessage() = getMovieAt(MOVIES_INITIAL_POSITION)?.title?.let {
//		getString(R.string.main_click_message, it)
//	}
//
//	private fun showToast(message: String?) {
//		when {
//			message.isNullOrEmpty() -> { showToast(getString(R.string.main_empty_message)) }
//			else -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//		}
//	}
//}
//
//private const val MOVIES_INITIAL_POSITION = 0