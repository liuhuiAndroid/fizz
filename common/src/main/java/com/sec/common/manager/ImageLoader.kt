package com.sec.common.manager

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ImageLoader {

    private val requestManager: RequestManager
    private val requestOptions by lazy {
        RequestOptions()
    }

    constructor(activity: FragmentActivity) {
        requestManager = Glide.with(activity)
    }

    constructor(fragment: Fragment) {
        requestManager = Glide.with(fragment)
    }

    fun display(
        view: ImageView,
        url: String?,
        placeholder: Int = 0,
        error: Int = 0,
        centerCrop: Boolean = false,
        radius: Int = 0,//圆角
        crossFade: Boolean = false,
        transformation: BitmapTransformation? = null
    ) {
        requestManager
            .load(url)
            .apply(
                requestOptions.autoClone()
                    .placeholder(placeholder)
                    .error(error)
                    .fallback(error)
            )
            .apply {
                if (crossFade) {
                    transition(DrawableTransitionOptions.withCrossFade())
                }
                if (radius > 0) {
                    transform(RoundedCorners(radius))
                }
                if (centerCrop) {
                    transform(CenterCrop())
                } else {
                    transformation?.also {
                        transform(it)
                    }
                }
            }
            .into(view)
    }

    fun resources(view: ImageView, id: Int) {
        requestManager.load(id).into(view)
    }
}