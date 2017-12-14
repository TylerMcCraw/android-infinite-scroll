package com.w3bshark.infinitescroll.api.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module for Gson
 *
 * Created by Tyler McCraw on 12/11/17.
 */
@Module
class GsonModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
