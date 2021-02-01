package com.sec.fizz.ui

import android.os.Bundle
import coil.imageLoader
import coil.request.ImageRequest
import com.sec.common.BaseActivity
import com.sec.fizz.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private val imageUrl =
        "https://pic.centanet.com/tianjin/postimage/xinfang/20200803/d72d7dfecfb0887d8e2bb14f14cad94f.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val disposable = imageView.load(imageUrl){
//            crossfade(true)
//            placeholder(R.drawable.ic_launcher)
//            transformations(CircleCropTransformation())
//        }
//        disposable.dispose()

        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .target(imageView)
            .listener(onSuccess = { request, metadata ->

            })
            .build()
        val disposable = imageLoader.enqueue(request)


//        request(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
//            onGranted {
//                lifecycleScope.launch {
//                    withContext(Dispatchers.IO) {
//                        delay(1000)
//                    }
//                    navigate(RoutePath.DYNAMIC_SYSTEM_MSG)
//                    finish()
//                }
//            }
//        }

    }

}