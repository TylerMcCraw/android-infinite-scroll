package com.w3bshark.infinitescroll.newsfeed

import com.w3bshark.infinitescroll.di.ActivityScoped
import com.w3bshark.infinitescroll.newsfeed.NewsFeedContract.Presenter
import dagger.Binds
import dagger.Module

/**
 * Dagger Module for the NewsFeed view
 * Created by Tyler McCraw on 12/10/17.
 */
@Module
abstract class NewsFeedModule {
    @ActivityScoped
    @Binds
    internal abstract fun newsFeedPresenter(presenter: NewsFeedPresenter): Presenter
}
