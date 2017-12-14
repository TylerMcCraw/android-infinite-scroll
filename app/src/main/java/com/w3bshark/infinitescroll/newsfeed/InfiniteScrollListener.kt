package com.w3bshark.infinitescroll.newsfeed

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Scroll listener for RecyclerView which must
 * load more items at the end, simulating an "inifinitely" or endless scrolling list
 *
 * Created by Tyler McCraw on 12/11/17.
 */
abstract class InfiniteScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val dataLoading: DataLoadingSubject
) : RecyclerView.OnScrollListener() {

    companion object {
        // The minimum number of items remaining before we should load more.
        private const val VISIBLE_THRESHOLD = 5
    }

    abstract fun onLoadMore()

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx The amount of horizontal scroll.
     * @param dy The amount of vertical scroll.
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        // bail out if scrolling upward or already loading data
        if (dy < 0 || dataLoading.isDataLoading()) {
            return
        }

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
            onLoadMore()
        }
    }
}
