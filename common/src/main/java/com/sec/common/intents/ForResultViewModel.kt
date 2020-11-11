package com.sec.common.intents

import android.util.SparseArray
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicInteger

class ForResultViewModel : ViewModel() {

    val atomicInteger by lazy {
        AtomicInteger(100)
    }

    val forResultSparseArray by lazy {
        SparseArray<ForResultCallback?>()
    }
}