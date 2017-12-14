package com.w3bshark.infinitescroll.newsfeed

import com.w3bshark.infinitescroll.api.data.IUser

/**
 * View Model for NewsFeed user
 *
 * Created by Tyler McCraw on 12/12/17.
 */
data class NewsFeedUser(
        override var id: Int,
        override var emailAddr: String?,
        override var firstName: String?,
        override var lastName: String?,
        override var photoUrl: String?
) : IUser
