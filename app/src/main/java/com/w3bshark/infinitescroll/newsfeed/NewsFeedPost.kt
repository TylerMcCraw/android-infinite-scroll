package com.w3bshark.infinitescroll.newsfeed

import com.w3bshark.infinitescroll.api.data.IPost
import com.w3bshark.infinitescroll.api.data.User
import com.w3bshark.infinitescroll.ext.isNotNullOrEmpty

/**
 * View Model for NewsFeed
 *
 * Created by Tyler McCraw on 12/11/17.
 */
data class NewsFeedPost(
        override val id: Int,
        override val createdAt: String,
        override val createdByUserId: Int,
        override val text: String?,
        override val imageUrl: String?,
        override val link: String?,
        override val user: User?
) : IPost {

    val type: PostType
        get() {
            return if (text.isNotNullOrEmpty()
                    && imageUrl.isNullOrEmpty()
                    && link.isNullOrEmpty()) {
                PostType.TEXT
            } else if (text.isNullOrEmpty()
                    && imageUrl.isNotNullOrEmpty()
                    && link.isNullOrEmpty()) {
                PostType.IMAGE
            } else if (text.isNullOrEmpty()
                    && imageUrl.isNullOrEmpty()
                    && link.isNotNullOrEmpty()) {
                PostType.LINK
            } else if (text.isNotNullOrEmpty()
                    && imageUrl.isNotNullOrEmpty()
                    && link.isNullOrEmpty()) {
                PostType.TEXTIMAGE
            } else if (text.isNullOrEmpty()
                    && imageUrl.isNotNullOrEmpty()
                    && link.isNotNullOrEmpty()) {
                PostType.LINKIMAGE
            } else {
                PostType.TEXT
            }
        }
}
