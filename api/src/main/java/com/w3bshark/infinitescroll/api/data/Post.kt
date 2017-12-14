package com.w3bshark.infinitescroll.api.data

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Post
 *
 * Created by Tyler McCraw on 12/10/17.
 */
@RealmClass
open class Post(
        @PrimaryKey
        override var id: Int = 0,
        override var createdAt: String = "",
        override var createdByUserId: Int = 0,
        override var text: String? = null,
        override var imageUrl: String? = null,
        override var link: String? = null,
        override var user: User? = null
) : RealmModel, IPost
