package com.leaudro.podcast.data

import com.leaudro.podcast.data.model.Podcast
import io.reactivex.Observable

class FakeDataSource : DataSource {
    override fun fetchPodcastList(): Observable<List<Podcast>> =
            Observable.just(listOf(
                    Podcast(1, "Brexit", "Very long subtitle talking about the podcast and it goes and goes and goes....", emptyList()),
                    Podcast(2, "Flintoff Savage and the Ping Pong Guy", "Another very very long subtitle talking about the podcast and it goes and goes and goes....", emptyList()),
                    Podcast(3, "The Listening", "yet another subtitle talking about the podcast and \nbreaking lines \nall the way", emptyList())
            ))
}