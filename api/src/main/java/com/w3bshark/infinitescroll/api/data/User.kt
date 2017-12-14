package com.w3bshark.infinitescroll.api.data

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * User
 *
 * Created by Tyler McCraw on 12/12/17.
 */
@RealmClass
open class User(
        @PrimaryKey
        override var id: Int = 0,
        override var emailAddr: String? = "",
        override var firstName: String? = "",
        override var lastName: String? = "",
        override var photoUrl: String? = ""
) : RealmModel, IUser
