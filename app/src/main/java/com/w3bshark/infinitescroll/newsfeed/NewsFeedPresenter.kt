package com.w3bshark.infinitescroll.newsfeed

import com.w3bshark.infinitescroll.api.data.source.PostDataSource
import com.w3bshark.infinitescroll.di.ActivityScoped
import com.w3bshark.infinitescroll.ext.applySchedulers
import com.w3bshark.infinitescroll.newsfeed.NewsFeedContract.Presenter
import com.w3bshark.infinitescroll.newsfeed.NewsFeedContract.View
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Presenter for NewsFeed View
 *
 * Created by Tyler McCraw on 12/11/17.
 */
@ActivityScoped
class NewsFeedPresenter
@Inject constructor(val postDataSource: PostDataSource) : Presenter {

    val userId = 1
    var oldestSyncedPostId: Int? = null
    var view: NewsFeedContract.View? = null

    private val subscriptions = CompositeDisposable()

    override fun fetchNewerPosts() {
        loadNewerPosts()
    }

    override fun fetchOlderPosts() {
        loadOlderPosts()
    }

    override fun takeView(view: View) {
        this.view = view
        loadOlderPosts()
    }

    override fun dropView() {
        subscriptions.clear()
        this.view = null
    }

    private fun loadNewerPosts() {
        subscriptions.add(postDataSource.getPosts(userId, sinceId = postDataSource.getNewestPostId())
                .flatMap { list ->
                    Observable.fromIterable(list)
                            .map { item ->
                                NewsFeedPost(item.id, item.createdAt, item.createdByUserId, item.text, item.imageUrl,
                                        item.link, item.user)
                            }
                            .toList()
                            .toObservable()
                }
                .applySchedulers()
                .subscribe { posts ->
                    view?.loadPosts(posts)
                    if (posts == null || posts.isEmpty()) {
                        // TODO we're either at the end or an error occurred
                    }
                })
    }

    private fun loadOlderPosts() {
        subscriptions.add(postDataSource.getPosts(userId, oldestSyncedId = oldestSyncedPostId)
                .flatMap { list ->
                    Observable.fromIterable(list)
                            .map { item ->
                                NewsFeedPost(item.id, item.createdAt, item.createdByUserId, item.text, item.imageUrl,
                                        item.link, item.user)
                            }
                            .toList()
                            .toObservable()
                }
                .applySchedulers()
                .subscribe { posts ->
                    view?.loadPosts(posts)
                    if (posts == null || posts.isEmpty()) {
                        // TODO we're either at the end or an error occurred
                    } else {
                        oldestSyncedPostId = posts[posts.size.minus(1)].id
                    }
                })
    }
}
