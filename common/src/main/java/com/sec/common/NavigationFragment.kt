package com.sec.common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.sec.common.intents.ForResultCallback
import com.sec.common.intents.ForResultViewModel

/**
 * 路由方式startActivityForResult
 */
class NavigationFragment : Fragment() {

    companion object {
        const val TAG = "NavigationFragment"
    }

    private val viewModel by lazy {
        activityViewModels<ForResultViewModel>().value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun openActivityForResult(path: String, bundle: Bundle, callback: ForResultCallback) {
        val requestCode = viewModel.atomicInteger.getAndIncrement().also {
            viewModel.forResultSparseArray.put(it, callback)
        }
        ARouter.getInstance().build(path).with(bundle).navigation(requireActivity(), requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callback: ForResultCallback? =
            viewModel.forResultSparseArray.get(requestCode, null).also {
                viewModel.forResultSparseArray.delete(requestCode)
            }
        callback?.let {
            when (resultCode) {
                Activity.RESULT_OK -> it.doOk(data)
                Activity.RESULT_CANCELED -> it.doCancel()
            }
        }
    }
}