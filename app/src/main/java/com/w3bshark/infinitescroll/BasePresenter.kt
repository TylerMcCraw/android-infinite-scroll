package com.w3bshark.infinitescroll

/**
 * Base Presenter contract
 *
 * Presenters must implement takeView and dropView
 * to properly handle setup and cleanup in regards to the
 * Android activity/fragment lifecycle
 *
 * Created by Tyler McCraw on 12/10/17.
 */
interface BasePresenter<T> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    fun takeView(view: T)

    /**
     * Drops the reference to the view when destroyed
     */
    fun dropView()
}
