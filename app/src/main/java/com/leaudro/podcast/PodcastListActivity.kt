package com.leaudro.podcast

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.leaudro.podcast.data.DataSource
import com.leaudro.podcast.data.FakeDataSource
import com.leaudro.podcast.data.model.Podcast
import com.leaudro.podcast.podcastdetails.PodcastDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface PodcastListView {
    fun showPodcastList(list: List<Podcast>)
    //TODO show progress/loading to user when fetching the list
    fun showError(t: Throwable)
}

class PodcastListActivity : AppCompatActivity(), PodcastListView {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    private val presenter by lazy { PodcastListPresenter(this, FakeDataSource()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.fetchPodcasts()
    }

    override fun showPodcastList(list: List<Podcast>) {
        recyclerView.adapter = PodcastListAdapter(list, {
            startActivity(Intent(this, PodcastDetailsActivity::class.java).apply {
                putExtra(PodcastDetailsActivity.EXTRA_PODCAST, it)
            })
        })
    }

    override fun showError(t: Throwable) {
        //TODO show proper error to user
    }
}

class PodcastListPresenter(val view: PodcastListView, val dataSource: DataSource) {

    fun fetchPodcasts() {
        dataSource.fetchPodcastList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showPodcastList(it)
                }, {
                    view.showError(it)
                })
    }
}
