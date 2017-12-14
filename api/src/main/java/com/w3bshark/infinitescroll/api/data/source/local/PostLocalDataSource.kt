package com.w3bshark.infinitescroll.api.data.source.local

import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.querySorted
import com.vicpin.krealmextensions.saveAll
import com.w3bshark.infinitescroll.api.data.Post
import com.w3bshark.infinitescroll.api.data.source.PostDataSource
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton manager class which governs
 * all local persistence of Post data
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Singleton
internal class PostLocalDataSource
@Inject constructor() : PostDataSource {

    override fun getPosts(userId: Int, sinceId: Int?, oldestSyncedId: Int?): Observable<List<Post>> =
            Observable.just(Post().queryAll())

    override fun getNewestPostId(): Int = Post().querySorted("createdAt", Sort.ASCENDING).first().id

    override fun savePosts(posts: List<Post>) = posts.saveAll()
}
