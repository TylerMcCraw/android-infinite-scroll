package com.w3bshark.infinitescroll.di

import com.w3bshark.infinitescroll.newsfeed.NewsFeedActivity
import com.w3bshark.infinitescroll.newsfeed.NewsFeedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent.
 *
 * The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all of these
 * subcomponents nor do you need to tell these subcomponents that AppComponent exists.
 *
 * We are also telling Dagger.Android that this generated Subcomponent needs to include the specified modules
 * and be aware of a scope annotation @ActivityScoped
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(NewsFeedModule::class)])
    abstract fun newsFeedActivity(): NewsFeedActivity
}
