package com.w3bshark.infinitescroll.newsfeed

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.w3bshark.infinitescroll.R
import com.w3bshark.infinitescroll.newsfeed.DataLoadingSubject.DataLoadingCallbacks
import com.w3bshark.infinitescroll.ui.GlideApp
import kotlinx.android.synthetic.main.list_item_post_text_image.view.postImageView
import kotlinx.android.synthetic.main.list_item_post_text_only.view.postTextView
import kotlinx.android.synthetic.main.list_item_post_text_only.view.userNameTextView
import kotlinx.android.synthetic.main.list_item_post_text_only.view.userProfileImageView
import kotlinx.android.synthetic.main.list_item_progress_bar.view.progressBarListItem

/**
 * Adapter for the NewsFeed RecyclerView
 *
 * Created by Tyler McCraw on 12/11/17.
 */
class NewsFeedAdapter(
        private val context: Context
) : RecyclerView.Adapter<NewsFeedViewHolder>(), PreloadModelProvider<NewsFeedPost>, DataLoadingCallbacks {

//    init {
//        setHasStableIds(true)
//    }

    var posts: MutableList<NewsFeedPost> = mutableListOf()
    private var showLoadingMore = false

    private val loadingMoreItemPosition: Int
        get() = if (showLoadingMore) itemCount - 1 else RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder =
            getViewHolder(viewType, LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) = when (getItemViewType(position)) {
        R.layout.list_item_progress_bar -> (holder as ProgressBarViewHolder).loadProgressBar()
        else -> (holder as PostViewHolder).bindPost(posts[position])
    }

    override fun getItemCount(): Int = posts.size + if (showLoadingMore) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (posts.size > 0 && position < posts.size) {
            posts[position].type.viewType
        } else {
            R.layout.list_item_progress_bar
        }
    }

//    override fun getItemId(position: Int): Long {
//        return posts[position].id
//    }

    private fun getViewHolder(viewType: Int, itemView: View): NewsFeedViewHolder = when (viewType) {
        R.layout.list_item_progress_bar -> ProgressBarViewHolder(itemView)
        R.layout.list_item_post_text_only -> TextViewHolder(itemView)
        R.layout.list_item_post_image_only -> ImageViewHolder(itemView)
        R.layout.list_item_post_link_only -> LinkViewHolder(itemView)
        R.layout.list_item_post_text_image -> TextImageViewHolder(itemView)
        R.layout.list_item_post_link_image -> LinkImageViewHolder(itemView)
        else -> TextViewHolder(itemView)
    }

    override fun getPreloadItems(position: Int): MutableList<NewsFeedPost> =
            posts.subList(position, minOf(posts.size, position + 5))

    override fun getPreloadRequestBuilder(item: NewsFeedPost?): RequestBuilder<Drawable>? {
        return GlideApp.with(context)
                .asDrawable()
                .load(item)
    }

    override fun dataStartedLoading() {
        if (showLoadingMore) {
            return
        }
        showLoadingMore = true
        notifyItemInserted(loadingMoreItemPosition)
    }

    override fun dataFinishedLoading() {
        if (!showLoadingMore) {
            return
        }
        val loadingPos = loadingMoreItemPosition
        showLoadingMore = false
        notifyItemRemoved(loadingPos)
    }
}

abstract class NewsFeedViewHolder(itemView: View) : ViewHolder(itemView)

abstract class PostViewHolder(itemView: View) : NewsFeedViewHolder(itemView) {
    abstract fun bindPost(post: NewsFeedPost)
}

class ProgressBarViewHolder(itemView: View) : NewsFeedViewHolder(itemView) {
    fun loadProgressBar() {
        itemView.progressBarListItem.visibility = if (adapterPosition > 0) View.VISIBLE else View.INVISIBLE
    }
}

class TextViewHolder(itemView: View) : PostViewHolder(itemView) {
    override fun bindPost(post: NewsFeedPost) {
        with(itemView) {
            post.user?.photoUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .circleCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(userProfileImageView)
                }
            }
            userNameTextView.text = resources.getString(R.string.post_username, post.user?.firstName, post.user?.lastName)
            postTextView.text = post.text
        }
    }
}

class ImageViewHolder(itemView: View) : PostViewHolder(itemView) {
    override fun bindPost(post: NewsFeedPost) {
        with(itemView) {
            post.user?.photoUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .circleCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(userProfileImageView)
                }
            }
            userNameTextView.text = resources.getString(R.string.post_username, post.user?.firstName, post.user?.lastName)
            post.imageUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .centerInside()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(postImageView)
                }
            }
        }
    }
}

class LinkViewHolder(itemView: View) : PostViewHolder(itemView) {
    override fun bindPost(post: NewsFeedPost) {
        with(itemView) {
            post.user?.photoUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .circleCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(userProfileImageView)
                }
            }
            userNameTextView.text = resources.getString(R.string.post_username, post.user?.firstName, post.user?.lastName)
            postTextView.text = post.link
        }
    }
}

class TextImageViewHolder(itemView: View) : PostViewHolder(itemView) {
    override fun bindPost(post: NewsFeedPost) {
        with(itemView) {
            post.user?.photoUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .circleCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(userProfileImageView)
                }
            }
            userNameTextView.text = resources.getString(R.string.post_username, post.user?.firstName, post.user?.lastName)
            postTextView.text = post.text
            post.imageUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .centerInside()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(postImageView)
                }
            }
        }
    }
}

class LinkImageViewHolder(itemView: View) : PostViewHolder(itemView) {
    override fun bindPost(post: NewsFeedPost) {
        with(itemView) {
            post.user?.photoUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .circleCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(userProfileImageView)
                }
            }
            userNameTextView.text = resources.getString(R.string.post_username, post.user?.firstName, post.user?.lastName)
            postTextView.text = post.link
            post.imageUrl?.let {
                if (it.isNotBlank()) {
                    GlideApp.with(context)
                            .load(it)
                            .centerInside()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(postImageView)
                }
            }
        }
    }
}
