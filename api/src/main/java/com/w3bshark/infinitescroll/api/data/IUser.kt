package com.w3bshark.infinitescroll.api.data

/**
 * Contract for User data model
 *
 * Created by Tyler McCraw on 12/12/17.
 */
interface IUser {
    var id: Int
    var emailAddr: String?
    var firstName: String?
    var lastName: String?
    var photoUrl: String?
}
