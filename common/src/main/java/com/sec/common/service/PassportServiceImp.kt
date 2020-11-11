package com.sec.common.service

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.sec.common.constant.RoutePath
import com.sec.common.intents.ForResultCallback
import com.sec.common.ktx.navigate
import com.sec.common.ktx.userToken
import com.sec.common.provider.PassportService
import com.sec.common.ui.PassportFragment

@Route(path = RoutePath.ACCOUNT_PASSPORT)
class PassportServiceImpl : PassportService {

    private lateinit var context: Context

    override fun navigate(activity: FragmentActivity, path: String, bundle: Bundle) {
        onPassport(activity) {
            onOk {
                activity.navigate(path, bundle)
            }
        }
    }

    override fun navigate(fragment: Fragment, path: String, bundle: Bundle) {
        onPassport(fragment) {
            onOk {
                fragment.navigate(path, bundle)
            }
        }
    }

    override fun onPassport(activity: FragmentActivity, callback: ForResultCallback.() -> Unit) {
        if (userToken().isEmpty()) {
            activity.loadPassPortFragment(callback)
        } else {
            val tempCallback = ForResultCallback()
            callback.invoke(tempCallback)
            tempCallback.doOk(null)
        }
    }

    override fun onPassport(fragment: Fragment, callback: ForResultCallback.() -> Unit) {
        if (userToken().isEmpty()) {
            fragment.requireActivity().loadPassPortFragment(callback)
        } else {
            val tempCallback = ForResultCallback()
            callback.invoke(tempCallback)
            tempCallback.doOk(null)
        }
    }

    override fun insertUserInfo(userString: String) {

    }

    override fun init(context: Context) {
        this.context = context
    }

    private fun FragmentActivity.loadPassPortFragment(callback: Function1<ForResultCallback, Unit>) {
        supportFragmentManager.beginTransaction()
            .add(
                PassportFragment().apply {
                    onResultCallback(callback)
                }, PassportFragment.TAG
            )
            .commitNowAllowingStateLoss()
    }

}