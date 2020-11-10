package com.sec.fizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.decode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvContent.text = "app版本号：" + decode(MMKVConst.VERSION_CODE, 0).toString()
    }

}