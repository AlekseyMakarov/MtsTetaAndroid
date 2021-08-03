package com.example.mtstetaandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.extensions.setMarginInDp


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