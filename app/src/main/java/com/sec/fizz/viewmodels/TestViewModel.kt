package com.sec.fizz.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sec.common.data.TestJson
import com.sec.common.network.ApiService
import com.sec.common.network.launchRequest
import com.sec.common.network.respond

class TestViewModel : ViewModel() {

    private val mTestDetail: MutableLiveData<TestJson> = MutableLiveData()

    fun subscribeTestDetail() = mTestDetail

    fun agentDetail(testNo: String) {
        launchRequest<TestJson> {
            loader {
                ApiService.commonApi.testJson(mutableMapOf("TestNo" to (testNo)))
            }
            onResult {
                respond(it, { data ->
                    mTestDetail.postValue(data)
                }, {

                })
            }
        }
    }

}