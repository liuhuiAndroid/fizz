package com.sec.common.intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ForResultFragment : Fragment() {

    private val viewModel by lazy {
        activityViewModels<ForResultViewModel>().value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun openActivityForResult(intent: Intent, callback: ForResultCallback) {
        val requestCode = viewModel.atomicInteger.getAndIncrement().also {
            viewModel.forResultSparseArray.put(it, callback)
        }
        startActivityForResult(intent, requestCode)
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