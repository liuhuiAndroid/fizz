package coil.singleton

import android.app.Application
import coil.ImageLoader

/**
 * A factory that creates new [ImageLoader] instances.
 *
 * To configure how the singleton [ImageLoader] is created **either**:
 * - Implement [ImageLoaderFactory] in your [Application].
 * - **Or** call [Coil.setImageLoader] with your [ImageLoaderFactory].
 */
interface ImageLoaderFactory {

    /**
     * Return a new [ImageLoader].
     */
    fun newImageLoader(): ImageLoader
}
