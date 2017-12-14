package com.w3bshark.infinitescroll

import android.app.Application
import com.w3bshark.infinitescroll.api.data.source.PostDataSourceModule
import com.w3bshark.infinitescroll.api.data.source.remote.GsonModule
import com.w3bshark.infinitescroll.api.data.source.remote.NetworkModule
import com.w3bshark.infinitescroll.di.ActivityBindingModule
import com.w3bshark.infinitescroll.di.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Dependency graph for our NewsFeed application
 * Defines necessary dependencies via modules
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Singleton
@Component(modules = [
    PostDataSourceModule::class,
    NetworkModule::class,
    GsonModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        fun gsonModule(gsonModule: GsonModule): AppComponent.Builder

        fun networkModule(networkModule: NetworkModule): AppComponent.Builder

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
