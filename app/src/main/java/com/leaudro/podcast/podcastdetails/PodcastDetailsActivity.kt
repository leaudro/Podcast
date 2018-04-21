package com.leaudro.podcast.podcastdetails

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.leaudro.podcast.R
import com.leaudro.podcast.data.model.Episode
import com.leaudro.podcast.data.model.Podcast

class PodcastDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PODCAST = "EXTRA_PODCAST"
    }

    private val podcast by lazy { intent.getParcelableExtra(EXTRA_PODCAST) as Podcast }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    private val soundPool by lazy {
        SoundPool.Builder()
                .setAudioAttributes(
                        AudioAttributes.Builder()
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                                .build()
                )
                .build()
    }

    private var playingSoundId: Int = 0
    private var playingEpisodeId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_detail)

        recyclerView.adapter = EpisodesAdapter(podcast.episodes, ::handleEpisodeClick)
    }

    private fun handleEpisodeClick(episode: Episode) {
        if (episode.isPlaying())
            stop()
        else
            episode.play()
    }

    private fun stop() {
        if (playingSoundId > 0) {
            soundPool.stop(playingSoundId)
            playingEpisodeId = 0
            playingSoundId = 0
        }
    }

    private fun Episode.isPlaying() = id == playingEpisodeId

    private fun Episode.play() {
        val soundPoolID = soundPool.load(this@PodcastDetailsActivity, audioId, 1)
        soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
            playingSoundId = soundPool.play(soundPoolID, 1f, 1f, 1, 0, 1f)
            playingEpisodeId = id
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
