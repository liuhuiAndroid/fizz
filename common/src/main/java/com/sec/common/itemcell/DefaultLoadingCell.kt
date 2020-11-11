package com.sec.common.itemcell

import android.view.View
import com.sec.common.R
import com.sec.common.adapter.RecyclerSupport
import com.sec.common.adapter.RecyclerVH
import com.sec.common.adapter.SortedItemCell
import kotlinx.android.synthetic.main.item_default_loading.view.*

/**
 * 默認Loading
 */
class DefaultLoadingCell : SortedItemCell {

    override fun order(): Long = 0

    override fun layoutResId() = R.layout.item_default_loading

    override fun itemId() = "DefaultLoadingCell"

    override fun itemContent() = "DefaultLoadingCell"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        DefaultLoadingVH(itemView, support)
}

class DefaultLoadingVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    init {
        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View?) {
                itemView.lottie_loading.cancelAnimation()
            }

            override fun onViewAttachedToWindow(v: View?) {
                itemView.lottie_loading.playAnimation()
            }
        })
    }
}