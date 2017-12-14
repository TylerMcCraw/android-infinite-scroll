package com.w3bshark.infinitescroll.newsfeed

import com.w3bshark.infinitescroll.BasePresenter
import com.w3bshark.infinitescroll.BaseView

/**
 * MVP contract for the NewsFeed screen
 *
 * Created by Tyler McCraw on 12/11/17.
 */
interface NewsFeedContract {

    interface View : BaseView<Presenter> {
        fun loadPosts(posts: List<NewsFeedPost>)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchOlderPosts()

        fun fetchNewerPosts()
    }
}
