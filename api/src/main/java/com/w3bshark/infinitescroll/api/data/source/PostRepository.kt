package com.w3bshark.infinitescroll.api.data.source

import com.w3bshark.infinitescroll.api.data.Post
import com.w3bshark.infinitescroll.api.data.source.local.Local
import com.w3bshark.infinitescroll.api.data.source.remote.Remote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton manager Repository which manages
 * all local and remote sources of Post data
 * See Repository pattern: @link https://msdn.microsoft.com/en-us/library/ff649690.aspx
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Singleton
internal class PostRepository
@Inject constructor(
        @Local val localSource: PostDataSource,
        @Remote val remoteSource: PostDataSource
) : PostDataSource {

    override fun getPosts(userId: Int, sinceId: Int?, oldestSyncedId: Int?): Observable<List<Post>> {
        return remoteSource.getPosts(userId, sinceId, oldestSyncedId).doOnNext { posts ->
            localSource.savePosts(posts)
        }.onErrorResumeNext(localSource.getPosts(userId, sinceId, oldestSyncedId))
    }

    override fun getNewestPostId(): Int = localSource.getNewestPostId()

    override fun savePosts(posts: List<Post>) {
        //N/A
    }
}
