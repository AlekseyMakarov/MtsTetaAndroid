package com.example.mtstetaandroid

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


fun View.setMarginInDp(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null,
    start: Float? = null,
    end: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
        start?.run { marginStart = dpToPx(this) }
        end?.run { marginEnd = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

class GenreAdapter() : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private var genres: List<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.genre_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), genres?.size ?: 0)
    }

    override fun getItemCount(): Int {
        return genres?.size ?: 0
    }

    fun setData(genres: List<String>): Unit {
        this.genres = genres
    }

    private fun getItem(position: Int): String = genres?.get(position) ?: ""

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreName: TextView = itemView.findViewById(R.id.textViewFilmGenreItem)
        fun bind(genre: String, size: Int) {
            when (this.adapterPosition) {
                0 -> {
                    itemView.setMarginInDp(start = 20F)
                }
                size - 1 -> {
                    itemView.setMarginInDp(end = 20F)
                }
            }
            genreName.text = genre
        }
    }

}