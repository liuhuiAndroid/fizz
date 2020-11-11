package com.sec.common.ktx

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.template.IProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.sec.common.NavigationFragment
import com.sec.common.intents.ForResultCallback

/**
 * ARouter navigation
 */
fun FragmentActivity.navigate(path: String, bundle: Bundle = Bundle()) {
    ARouter.getInstance().build(path).with(bundle).navigation(this)
}

/**
 * ARouter navigation
 */
fun Fragment.navigate(path: String, bundle: Bundle = Bundle()) {
    ARouter.getInstance().build(path).with(bundle).navigation(requireActivity())
}

/**
 * ARouter navigation
 */
inline fun FragmentActivity.navigateForResult(
    path: String, bundle: Bundle = Bundle(),
    crossinline callback: ForResultCallback.() -> Unit
) {
    getNavigationFragment(this).openActivityForResult(
        path,
        bundle,
        ForResultCallback().apply(callback)
    )
}

/**
 * ARouter navigation
 */
inline fun Fragment.navigateForResult(
    path: String, bundle: Bundle = Bundle(),
    crossinline callback: ForResultCallback.() -> Unit
) {
    getNavigationFragment(requireActivity()).openActivityForResult(
        path,
        bundle,
        ForResultCallback().apply(callback)
    )
}

/**
 * IProvider
 */
inline fun <reified T : IProvider> provider(): T? {
    return ARouter.getInstance().navigation(T::class.java)
}

/**
 * 获取导航Fragment
 */
fun getNavigationFragment(activity: FragmentActivity): NavigationFragment {
    var fragment = activity.supportFragmentManager.findFragmentByTag(NavigationFragment.TAG)
    if (fragment == null) {
        fragment = NavigationFragment()
        activity.supportFragmentManager.beginTransaction().add(fragment, NavigationFragment.TAG)
            .commitNow()
    }
    return fragment as NavigationFragment
}
