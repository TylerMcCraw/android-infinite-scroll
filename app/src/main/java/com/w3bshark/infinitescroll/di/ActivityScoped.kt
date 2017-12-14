package com.w3bshark.infinitescroll.di

import javax.inject.Scope

/**
 * Annotation for Activity-scoped dependencies
 * used in conjunction with Dagger modules
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ActivityScoped
