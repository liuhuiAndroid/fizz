package com.sec.news.dynamic.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sec.common.data.FizzResponse
import com.sec.common.data.response.OfficialNewsJson
import com.sec.common.network.ApiService
import com.sec.common.network.launchRequest

class OfficialMsgViewModel(application: Application) : AndroidViewModel(application) {
    val mOfficialNewsJson: MutableLiveData<FizzResponse<List<OfficialNewsJson>>> =
        MutableLiveData()

    var total: Int = 0
    var pagerIndex: Int = 0

    fun getOfficialNewsList(param: String) {
        launchRequest<List<OfficialNewsJson>> {
            loader {
                ApiService.publicApi.officialNewsList(param)
            }
            onResult {
                total = it.Total
                mOfficialNewsJson.postValue(it)
            }
        }
    }
}