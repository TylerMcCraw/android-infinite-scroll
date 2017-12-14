package com.w3bshark.infinitescroll.ext

import android.view.View

/**
 * View extension functions
 *
 * Created by Tyler McCraw on 12/11/17.
 */

fun View.isVisible() = visibility == View.VISIBLE
fun View.isGone() = visibility == View.GONE
fun View.isHidden() = visibility == View.INVISIBLE

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showIf(condition: Boolean) {
    if (condition) show() else hide()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.hideIf(condition: Boolean) {
    if (condition) hide() else show()
}
