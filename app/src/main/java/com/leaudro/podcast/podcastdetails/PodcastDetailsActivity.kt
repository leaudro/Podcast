package com.leaudro.podcast.podcastdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.leaudro.podcast.R
import com.leaudro.podcast.data.model.Episode
import com.leaudro.podcast.data.model.Podcast
import com.leaudro.podcast.utils.showToast

class PodcastDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PODCAST = "EXTRA_PODCAST"
    }

    private val podcast by lazy { intent.getParcelableExtra(EXTRA_PODCAST) as Podcast }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_detail)

        recyclerView.adapter = EpisodesAdapter(podcast.episodes, ::handleEpisodeClick)
    }

    private fun handleEpisodeClick(episode: Episode) {
        showToast("Playing ${episode.title}")
    }
}

