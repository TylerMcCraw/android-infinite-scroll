package com.w3bshark.infinitescroll.api.data.source

import com.w3bshark.infinitescroll.api.data.source.local.Local
import com.w3bshark.infinitescroll.api.data.source.local.PostLocalDataSource
import com.w3bshark.infinitescroll.api.data.source.remote.PostRemoteDataSource
import com.w3bshark.infinitescroll.api.data.source.remote.Remote
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger Module for Post Data Sources
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Module
class PostDataSourceModule {

    @Singleton
    @Provides
    internal fun providePostDataSource(@Local localDataSource: PostDataSource,
            @Remote remoteDataSource: PostDataSource): PostDataSource {
        return PostRepository(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    @Local
    internal fun providePostLocalDataSource(): PostDataSource {
        return PostLocalDataSource()
    }

    @Singleton
    @Provides
    @Remote
    internal fun providePostRemoteDataSource(retrofit: Retrofit): PostDataSource {
        return PostRemoteDataSource(retrofit)
    }
}
