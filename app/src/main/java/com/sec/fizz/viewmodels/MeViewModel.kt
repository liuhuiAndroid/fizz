package com.sec.fizz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is me Fragment"
    }
    val text: LiveData<String> = _text
}