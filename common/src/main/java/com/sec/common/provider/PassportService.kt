package com.sec.common.provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.template.IProvider
import com.sec.common.intents.ForResultCallback

/**
 * 用户登录权限服务
 */
interface PassportService : IProvider {

    fun navigate(activity: FragmentActivity, path: String, bundle: Bundle)

    fun navigate(fragment: Fragment, path: String, bundle: Bundle)

    fun onPassport(activity: FragmentActivity, callback: ForResultCallback.() -> Unit)

    fun onPassport(fragment: Fragment, callback: ForResultCallback.() -> Unit)

    fun insertUserInfo(userString: String)
}