package com.sec.common.manager

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.singleton.load
import coil.transform.Transformation

class ImageLoader {

    fun display(
        view: ImageView,
        url: String?,
        @DrawableRes placeholder: Int = 0,
        @DrawableRes errorImage: Int = 0,
        transformation: Transformation? = null,
    ) {
        view.load(url) {
            // 淡入淡出
            crossfade(true)
            placeholder(placeholder)
            error(errorImage)
            transformation?.let {
                transformations(it)
            }
        }
    }

    fun resources(view: ImageView, @DrawableRes id: Int) {
        view.load(id)
    }
}