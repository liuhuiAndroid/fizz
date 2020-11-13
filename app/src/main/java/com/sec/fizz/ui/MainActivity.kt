package com.sec.fizz.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sec.common.BaseActivity
import com.sec.common.constant.MMKVConst
import com.sec.common.constant.RoutePath
import com.sec.common.ktx.decode
import com.sec.common.ktx.provider
import com.sec.common.ktx.showToast
import com.sec.common.provider.PassportService
import com.sec.fizz.R
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RoutePath.APP_MAIN)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
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