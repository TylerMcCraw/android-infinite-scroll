package com.w3bshark.infinitescroll

import com.w3bshark.infinitescroll.api.data.source.remote.GsonModule
import com.w3bshark.infinitescroll.api.data.source.remote.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Custom Application class which handles setup for
 * Dependency Injection and other global singleton instances
 *
 * Created by Tyler McCraw on 12/10/17.
 */
class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initSingletons()
    }

    private fun initSingletons() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build())

        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                // Show line number in logs
                return "${super.createStackElementTag(element)}:${element.lineNumber}"
            }
        })
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .networkModule(NetworkModule(BuildConfig.HOSTNAME))
                .gsonModule(GsonModule())
                .build()
    }
}
