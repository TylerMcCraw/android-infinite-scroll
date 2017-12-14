package com.w3bshark.infinitescroll.api.data.source.remote

import javax.inject.Qualifier

/**
 * Remote annotation
 *
 * Signifies that a data source is used
 * for remote data (i.e. server resources)
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Remote
