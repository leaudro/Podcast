package com.leaudro.podcast.data

import com.leaudro.podcast.R
import com.leaudro.podcast.data.model.Episode
import com.leaudro.podcast.data.model.Podcast
import io.reactivex.Observable

class FakeDataSource : DataSource {
    override fun fetchPodcastList(): Observable<List<Podcast>> =
            Observable.just(listOf(
                    Podcast(1, "Brexit", "Very long subtitle talking about the podcast and it goes and goes and goes....", getFakeEpisodesList(1)),
                    Podcast(2, "Flintoff Savage and the Ping Pong Guy", "Another very very long subtitle talking about the podcast and it goes and goes and goes....", getFakeEpisodesList(2)),
                    Podcast(3, "The Listening", "yet another subtitle talking about the podcast and \nbreaking lines \nall the way", getFakeEpisodesList(3))
            ))
}

fun getFakeEpisodesList(podcastId: Int): List<Episode> =
    when (podcastId) {
        1 -> listOf(
                Episode(1, "Could Brexit Change Our Travel Plans", R.raw.brexit1),
                Episode(2, "Can Scotland Do Brexit Differently", R.raw.brexit2))
        2 -> listOf(
                Episode(3, "Preview Flintoff Diving Have You No Self Respect", R.raw.flint1),
                Episode(4, "Smells Like Team Spirit", R.raw.flint2))
        3 -> listOf(
                Episode(5, "Christmas Day Omnibus", R.raw.thelistening1),
                Episode(6, "Omnibus Jo And Andy Divorce", R.raw.thelistening2),
                Episode(7, "Omnibus Contending With Death", R.raw.thelistening3))
        else -> emptyList()
    }