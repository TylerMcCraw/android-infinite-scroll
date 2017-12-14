package com.w3bshark.infinitescroll.newsfeed

/**
 * Contract for classes offering knowledge of data loading state
 *
 * Created by Tyler McCraw on 12/14/17.
 */
interface DataLoadingSubject {
    fun isDataLoading(): Boolean

    interface DataLoadingCallbacks {
        fun dataStartedLoading()

        fun dataFinishedLoading()
    }
}
