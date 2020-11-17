@file:JvmName("Contexts")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package coil.singleton

import android.content.Context
import coil.ImageLoader

/**
 * Get the singleton [ImageLoader]. This is an alias for [Coil.imageLoader].
 */
inline val Context.imageLoader: ImageLoader
    @JvmName("imageLoader") get() = Coil.imageLoader(this)
