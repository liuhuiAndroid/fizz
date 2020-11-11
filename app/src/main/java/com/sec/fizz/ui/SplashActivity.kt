package com.sec.fizz.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sec.common.BaseActivity
import com.sec.common.constant.RoutePath
import com.sec.common.ktx.navigate
import com.sec.fizz.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000)
            }
            navigate(RoutePath.APP_MAIN)
            finish()
        }
    }

}