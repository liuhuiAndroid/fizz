package com.sec.common.itemcell

import android.view.View
import com.sec.common.R
import com.sec.common.adapter.ItemCell
import com.sec.common.adapter.RecyclerSupport
import com.sec.common.adapter.RecyclerVH
import com.sec.common.ktx.debounceClick
import kotlinx.android.synthetic.main.item_default_error.view.*

/**
 * 默認Error - 网络出错
 */
class DefaultErrorNetCell : ItemCell {

    override fun layoutResId() = R.layout.item_default_error

    override fun itemId() = "DefaultErrorCell"

    override fun itemContent() = "DefaultErrorCell"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        DefaultErrorNetVH(itemView, support)
}

class DefaultErrorNetVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {

    init {
        itemView.reload.debounceClick {
            support.retry?.invoke()
        }
    }
}