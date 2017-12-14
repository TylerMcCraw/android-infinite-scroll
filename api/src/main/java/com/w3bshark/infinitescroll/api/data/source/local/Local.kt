package com.w3bshark.infinitescroll.api.data.source.local

import javax.inject.Qualifier

/**
 * Local annotation
 *
 * Signifies that a data source is used
 * for local data
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Local
