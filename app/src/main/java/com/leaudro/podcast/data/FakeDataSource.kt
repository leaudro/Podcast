package com.leaudro.podcast.data

import com.leaudro.podcast.data.model.Podcast
import io.reactivex.Observable

class FakeDataSource : DataSource {
    override fun fetchPodcastList(): Observable<List<Podcast>> =
            Observable.just(listOf(
                    Podcast("1"),
                    Podcast("2"),
                    Podcast("3"),
                    Podcast("4"),
                    Podcast("5"),
                    Podcast("6"),
                    Podcast("7"),
                    Podcast("8"),
                    Podcast("9"),
                    Podcast("10")
            ))
}