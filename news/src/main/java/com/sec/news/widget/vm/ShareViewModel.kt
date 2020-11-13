package com.sec.news.widget.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sec.news.data.ShareTypeInfo

class ShareViewModel : ViewModel() {

    private val mLiveData: MutableLiveData<ShareTypeInfo> = MutableLiveData()

    fun subscribe(): MutableLiveData<ShareTypeInfo> {
        return mLiveData
    }

    fun publish(shareTypeInfo: ShareTypeInfo) {
        mLiveData.postValue(shareTypeInfo)
    }
}