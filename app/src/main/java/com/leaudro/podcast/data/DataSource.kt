package com.leaudro.podcast.data

import com.leaudro.podcast.data.model.Podcast
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


interface DataSource {
    fun fetchPodcastList(): Observable<List<Podcast>>
}

val dataSource: DataSource
    get() {
        val retrofit = Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(DataSource::class.java)
    }