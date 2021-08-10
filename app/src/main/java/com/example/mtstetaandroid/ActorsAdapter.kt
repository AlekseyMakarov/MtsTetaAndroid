package com.example.mtstetaandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtstetaandroid.extensions.setMarginInDp


class ActorsAdapter() : RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    private var actors: List<Pair<String, Int>>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.actor_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorsAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), actors?.size ?: 0)
    }

    override fun getItemCount(): Int {
        return actors?.size ?: 0
    }

    fun setData(actors: List<Pair<String, Int>>): Unit {
        this.actors = actors
    }

    private fun getItem(position: Int): Pair<String, Int> = actors?.get(position) ?: Pair("", 0)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actorName: TextView = itemView.findViewById(R.id.textViewActorName)
        private val actorImage: ImageView = itemView.findViewById(R.id.imageViewActor)
        fun bind(actor: Pair<String, Int>, size: Int) {
            when (this.adapterPosition) {
                0 -> {
                    itemView.setMarginInDp(start = 20F)
                }
                size - 1 -> {
                    itemView.setMarginInDp(end = 20F)
                }
            }
            actorName.text = actor.first
            actorImage.setImageResource(actor.second)
        }
    }

}