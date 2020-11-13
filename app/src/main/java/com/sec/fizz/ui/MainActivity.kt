package com.sec.fizz.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.sec.common.BaseActivity
import com.sec.common.constant.RoutePath
import com.sec.fizz.R
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RoutePath.APP_MAIN)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView.setupWithNavController(findNavController(R.id.navHostFragment))
//        provider<PassportService>()?.onPassport(this) {
//            onOk {
//                showToast("登录成功,返回首页了")
//            }
//            onCancel{
//                finish()
//            }
//        }
    }

}