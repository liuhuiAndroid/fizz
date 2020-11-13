package com.sec.fizz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DashboardViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}