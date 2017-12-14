package com.w3bshark.infinitescroll.newsfeed

import android.support.annotation.LayoutRes
import com.w3bshark.infinitescroll.R

/**
 * Post Type
 *
 * Represents a view type for items in the
 * NewsFeed RecyclerView
 *
 * Created by Tyler McCraw on 12/13/17.
 */
enum class PostType(@LayoutRes val viewType: Int) {
    TEXT(R.layout.list_item_post_text_only),
    IMAGE(R.layout.list_item_post_image_only),
    LINK(R.layout.list_item_post_link_only),
    TEXTIMAGE(R.layout.list_item_post_text_image),
    LINKIMAGE(R.layout.list_item_post_link_image)
}
