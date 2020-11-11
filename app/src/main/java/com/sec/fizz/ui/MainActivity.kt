package com.sec.fizz.ui

import android.os.Bundle
import com.sec.common.BaseActivity
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.decode
import com.sec.fizz.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvContent.text = "DEVICE_ID：" + decode(MMKVConst.DEVICE_ID, "")
    }

}