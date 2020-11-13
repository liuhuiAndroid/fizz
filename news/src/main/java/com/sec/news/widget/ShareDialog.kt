package com.sec.news.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sec.news.R
import com.sec.news.widget.vm.ShareViewModel
import kotlinx.android.synthetic.main.dialog_share.*

class ShareDialog : BottomSheetDialogFragment() {

    private val mShareViewModel by activityViewModels<ShareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareWx.setOnClickListener {
            //分享到好友
        }
        shareFriend.setOnClickListener {
            //分享到朋友圈
        }
        tv_cancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

}