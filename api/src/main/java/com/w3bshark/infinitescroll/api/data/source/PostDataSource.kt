package com.w3bshark.infinitescroll.api.data.source

import com.w3bshark.infinitescroll.api.data.Post
import io.reactivex.Observable

/**
 * Contract which exposes common functions for managing Post data
 *
 * This is provided via {@link PostDataSourceModule}
 *
 * Created by Tyler McCraw on 12/10/17.
 */
interface PostDataSource {

    fun getPosts(userId: Int, sinceId: Int? = null, oldestSyncedId: Int? = null): Observable<List<Post>>

    fun savePosts(posts: List<Post>)

    fun getNewestPostId(): Int
}
