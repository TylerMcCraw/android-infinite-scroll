package com.w3bshark.infinitescroll.ext

/**
 * String extension functions
 *
 * Created by Tyler McCraw on 12/11/17.
 */
fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}
