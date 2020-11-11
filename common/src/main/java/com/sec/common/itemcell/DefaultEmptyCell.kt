package com.sec.common.itemcell

import android.view.View
import com.sec.common.R
import com.sec.common.adapter.RecyclerSupport
import com.sec.common.adapter.RecyclerVH
import com.sec.common.adapter.SortedItemCell

/**
 * 默認Empty
 */
class DefaultEmptyCell : SortedItemCell {

    override fun order(): Long = 0

    override fun layoutResId() = R.layout.item_default_empty

    override fun itemId() = "DefaultEmptyCell"

    override fun itemContent() = "DefaultEmptyCell"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        DefaultEmptyVH(itemView, support)
}

class DefaultEmptyVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support)