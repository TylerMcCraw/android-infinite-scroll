package com.w3bshark.infinitescroll.api.data.source.remote

import com.w3bshark.infinitescroll.api.data.Post
import com.w3bshark.infinitescroll.api.data.source.PostDataSource
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton manager class which governs all
 * Post-specific network calls for our NewsFeed API
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Singleton
internal class PostRemoteDataSource
@Inject constructor(retrofit: Retrofit) : PostDataSource {

    companion object {
        private const val BATCH_COUNT = 10
    }

    private val service: INewsFeedService = retrofit.create(INewsFeedService::class.java)

    override fun getPosts(userId: Int, sinceId: Int?, oldestSyncedId: Int?): Observable<List<Post>> {
        return when {
        // Fetch newer posts
            sinceId != null -> service.getUserFeed(userId, BATCH_COUNT, sinceId = sinceId)
        // Fetch older posts
            oldestSyncedId != null -> service.getUserFeed(userId, BATCH_COUNT, maxId = oldestSyncedId)
            else -> service.getUserFeed(userId, BATCH_COUNT)
        }
    }

    override fun getNewestPostId(): Int = 0 //N/A

    override fun savePosts(posts: List<Post>) {} //N/A for now
}
