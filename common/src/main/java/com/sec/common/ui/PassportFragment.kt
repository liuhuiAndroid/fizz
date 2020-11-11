package com.sec.common.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sec.common.R
import com.sec.common.constant.RoutePath
import com.sec.common.intents.ForResultCallback
import com.sec.common.room.FizzDatabase

class PassportFragment : Fragment() {

    companion object {
        const val TAG = "TAG_PassportFragment"
        const val REQUEST_CODE = 123
    }

    private val loadingDialog by lazy {
        MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.layout_loading_dialog)
            .setBackground(ColorDrawable(Color.TRANSPARENT))
            .setCancelable(false)
            .create()
    }

    private var block: Function1<ForResultCallback, Unit>? = null

    fun onResultCallback(callback: Function1<ForResultCallback, Unit>) {
        block = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 用户登录订阅
        FizzDatabase.getInstance(requireActivity()).accountDao()
            .subscribeAccount().observe(this, Observer {
                if (it.isNotEmpty()) {
                    /* 用户登录成功 */
                    callBack(true)
                }
            })
        toLogin()
    }

    private fun toLogin() {
        ARouter.getInstance().build(RoutePath.APP_LOGIN).navigation(requireActivity(), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    // 挪到用户登录订阅
                    // callBack(true)
                }
                Activity.RESULT_CANCELED -> {
                    callBack(false)
                }
            }
        }
    }

    private fun callBack(success: Boolean) {
        loadingDialog.dismiss()
        val tempCallBack = ForResultCallback()
        block?.invoke(tempCallBack)
        tempCallBack.apply {
            if (success)
                doOk(null)
            else
                doCancel()
        }
        parentFragmentManager.beginTransaction().remove(this).commitNowAllowingStateLoss()
    }

}