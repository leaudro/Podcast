package com.leaudro.podcast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leaudro.podcast.data.model.Podcast
import com.leaudro.podcast.utils.inflate

class PodcastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textView by lazy { itemView.findViewById<TextView>(android.R.id.text1) }

    fun bind(podcast: Podcast, listener: (Podcast) -> Unit) {
        textView.text = podcast.id
        itemView.setOnClickListener { listener(podcast) }
    }
}

class PodcastListAdapter(private val list: List<Podcast>, val listener: (Podcast) -> Unit) : RecyclerView.Adapter<PodcastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder =
            PodcastViewHolder(parent.inflate(android.R.layout.activity_list_item))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }
}
