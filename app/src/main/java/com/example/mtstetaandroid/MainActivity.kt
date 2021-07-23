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
import com.example.mtstetaandroid.data.dto.MovieDto
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MainActivity : AppCompatActivity() {
	private lateinit var moviesModel: MoviesModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.movies_list)

		val recycler = findViewById<RecyclerView>(R.id.recyclerViewGenres)
		val genres = listOf("боевик", "драма", "мелодрама", "ужасы", "триллеры", "комедии", "хаус", "про войну", "документальные")
		val adapter = GenreAdapter()
		adapter.setData(genres)
		recycler.adapter = adapter
		recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


		val recyclerMovie = findViewById<RecyclerView>(R.id.recyclerViewMovies)
		initDataSource()

		val movies = moviesModel.getMovies()
		val adapterMovie = MovieAdapter()
		adapterMovie.setOnClickListener (::showToast)
		adapterMovie.setData(movies)
		recyclerMovie.addItemDecoration(object : RecyclerView.ItemDecoration() {
			override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
				outRect.set(
					if (itemPosition % 2 == 0) dpToPx(20f) else dpToPx(10f),
					if (itemPosition < 2) dpToPx(0f) else dpToPx(50f),
					if (itemPosition % 2 == 1) dpToPx(20f) else dpToPx(10f),
					when (parent.adapter!!.itemCount%2){
						0 -> if (itemPosition == parent.adapter!!.itemCount - 1 || itemPosition == parent.adapter!!.itemCount - 2) dpToPx(16f) else 0
						1-> if (itemPosition == parent.adapter!!.itemCount - 1) dpToPx(16f) else 0
						else -> 0
					}
				)
			}
		})
		recyclerMovie.adapter = adapterMovie
		recyclerMovie.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
		

//		setupViews()
//		setupListeners()
	}
	
	private fun initDataSource() {
		moviesModel = MoviesModel(MoviesDataSourceImpl())
	}

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
	fun showToast(message: String?) {
		when {
			message.isNullOrEmpty() -> { showToast(getString(R.string.main_empty_message)) }
			else -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
		}
	}
}
//
//private const val MOVIES_INITIAL_POSITION = 0