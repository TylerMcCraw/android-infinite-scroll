package com.w3bshark.infinitescroll.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Dagger Module for Application Context
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}
