package com.leaudro.podcast.podcastdetails

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leaudro.podcast.R
import com.leaudro.podcast.data.model.Episode
import com.leaudro.podcast.utils.inflate


class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val titleTextView by lazy { itemView.findViewById<TextView>(R.id.text_title) }

    fun bind(episode: Episode, listener: (Episode) -> Unit) {
        with (episode) {
            titleTextView.text = title
            itemView.setOnClickListener { listener(this) }
        }
    }
}

class EpisodesAdapter(private val list: List<Episode>, private val listener: (Episode) -> Unit) : RecyclerView.Adapter<EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
            EpisodeViewHolder(parent.inflate(R.layout.episode_list_item))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
}
