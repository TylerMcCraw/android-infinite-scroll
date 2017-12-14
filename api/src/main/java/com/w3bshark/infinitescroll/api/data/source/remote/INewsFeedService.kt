package com.w3bshark.infinitescroll.api.data.source.remote

import com.w3bshark.infinitescroll.api.data.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * NewsFeed service
 *
 * Contains functions for each remote service endpoint
 *
 * Created by Tyler McCraw on 12/10/17.
 */
internal interface INewsFeedService {
    @GET("/{userId}/feed")
    fun getUserFeed(@Path("userId") userId: Int,
            @Query("count") count: Int,
            @Query("since_id") sinceId: Int? = null,
            @Query("max_id") maxId: Int? = null): Observable<List<Post>>
}
