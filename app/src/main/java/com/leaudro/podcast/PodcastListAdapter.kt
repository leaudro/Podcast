package com.leaudro.podcast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leaudro.podcast.data.model.Podcast
import com.leaudro.podcast.utils.inflate

class PodcastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val titleTextView by lazy { itemView.findViewById<TextView>(R.id.text_title) }
    private val subtitleTextView by lazy { itemView.findViewById<TextView>(R.id.text_subtitle) }

    fun bind(podcast: Podcast, listener: (Podcast) -> Unit) {
        with (podcast) {
            titleTextView.text = title
            subtitleTextView.text = subtitle
            itemView.setOnClickListener { listener(this) }
        }
    }
}

class PodcastListAdapter(private val list: List<Podcast>, private val listener: (Podcast) -> Unit) : RecyclerView.Adapter<PodcastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder =
            PodcastViewHolder(parent.inflate(R.layout.podcast_list_item))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
}
