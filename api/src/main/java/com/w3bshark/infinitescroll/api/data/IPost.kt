package com.w3bshark.infinitescroll.api.data

/**
 * Contract for Post data model
 *
 * Created by Tyler McCraw on 12/11/17.
 */
interface IPost {
    val id: Int
    val createdAt: String
    val createdByUserId: Int
    val text: String?
    val imageUrl: String?
    val link: String?
    val user: User?
}
