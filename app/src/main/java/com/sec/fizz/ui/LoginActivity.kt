package com.sec.fizz.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.sec.common.BaseActivity
import com.sec.common.constant.RoutePath
import com.sec.common.ktx.showToast
import com.sec.fizz.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Route(path = RoutePath.APP_LOGIN)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000)
            }
            showToast("登录成功")
            setResult(RESULT_OK)
            finish()
        }
    }

}