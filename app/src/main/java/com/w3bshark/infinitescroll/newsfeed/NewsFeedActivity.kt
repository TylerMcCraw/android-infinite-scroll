package com.w3bshark.infinitescroll.newsfeed

import android.content.res.ColorStateList
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.OvershootInterpolator
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.w3bshark.infinitescroll.R
import com.w3bshark.infinitescroll.R.layout
import com.w3bshark.infinitescroll.ext.hide
import com.w3bshark.infinitescroll.ext.isHidden
import com.w3bshark.infinitescroll.ext.isVisible
import com.w3bshark.infinitescroll.ext.show
import com.w3bshark.infinitescroll.newsfeed.NewsFeedContract.Presenter
import com.w3bshark.infinitescroll.ui.GlideApp
import dagger.android.support.DaggerAppCompatActivity
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator
import kotlinx.android.synthetic.main.activity_news_feed.toolbar
import kotlinx.android.synthetic.main.content_news_feed.newsFeedRecyclerView
import kotlinx.android.synthetic.main.content_news_feed.progressBar
import kotlinx.android.synthetic.main.content_news_feed.refreshLayout
import javax.inject.Inject

/**
 * NewsFeed Activity
 *
 * Created by Tyler McCraw on 12/11/17.
 */
class NewsFeedActivity : DaggerAppCompatActivity(), NewsFeedContract.View,
        DataLoadingSubject, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val MAX_PRELOAD_IMAGE_COUNT = 6
    }

    @Inject
    lateinit var presenter: Presenter

    private val adapter = NewsFeedAdapter(context = this)
    private var dataLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_news_feed)
        setSupportActionBar(toolbar)

        setUpView()
    }

    override fun onStart() {
        super.onStart()
        presenter.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun onRefresh() {
        presenter.fetchNewerPosts()
    }

    private fun setUpView() {
        refreshLayout.apply {
            setColorSchemeColors(ContextCompat.getColor(this@NewsFeedActivity, R.color.colorAccent))
            setOnRefreshListener(this@NewsFeedActivity)
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val preloader = RecyclerViewPreloader<NewsFeedPost>(
                GlideApp.with(this), adapter, ViewPreloadSizeProvider<NewsFeedPost>(), MAX_PRELOAD_IMAGE_COUNT)

        newsFeedRecyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@NewsFeedActivity, DividerItemDecoration.VERTICAL))
            this.layoutManager = layoutManager
            addOnScrollListener(preloader)
            addOnScrollListener(object : InfiniteScrollListener(layoutManager, this@NewsFeedActivity) {
                override fun onLoadMore() {
                    loadData()
                }
            })
            adapter = this@NewsFeedActivity.adapter
            itemAnimator = FadeInUpAnimator(OvershootInterpolator(1f)).apply {
                addDuration = 300
                removeDuration = 300
            }
        }
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            progressBar.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }

    private fun loadData() {
        dataLoading = true
        refreshLayout.isRefreshing = false
        if (!newsFeedRecyclerView.isVisible()) {
            progressBar.show()
        }
        if (adapter.itemCount != 0) {
            newsFeedRecyclerView.post { adapter.dataStartedLoading() }
        }
        presenter.fetchOlderPosts()
    }

    override fun loadPosts(posts: List<NewsFeedPost>) {
        if (progressBar.isVisible()) progressBar.hide()
        if (newsFeedRecyclerView.isHidden()) newsFeedRecyclerView.show()
        dataLoading = false
        refreshLayout.isRefreshing = false
        adapter.apply {
            dataFinishedLoading()
            this.posts.addAll(posts)
            notifyItemRangeChanged(this.itemCount - posts.size, posts.size)
        }
    }

    override fun isDataLoading(): Boolean = dataLoading
}
