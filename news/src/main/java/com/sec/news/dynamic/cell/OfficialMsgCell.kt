package com.sec.news.dynamic.cell

import android.annotation.SuppressLint
import android.view.View
import com.sec.common.adapter.ItemCell
import com.sec.common.adapter.RecyclerSupport
import com.sec.common.adapter.RecyclerVH
import com.sec.common.data.response.OfficialNewsJson
import com.sec.common.ktx.debounceClick
import com.sec.common.ktx.gone
import com.sec.common.ktx.timeRule
import com.sec.common.ktx.visible
import com.sec.news.R
import kotlinx.android.synthetic.main.item_dynamic_system_news.view.*
import kotlinx.android.synthetic.main.item_dynamic_time_news.view.*

/**
 * 系统消息
 */
class OfficialMsgCell(val item: OfficialNewsJson) : ItemCell {
    override fun layoutResId(): Int = R.layout.item_dynamic_system_news

    override fun itemId() = item.SysInfoId ?: ""

    override fun itemContent(): String = item.SysInfoId ?: ""

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport): RecyclerVH =
        OfficialMsgBV(itemView, support)
}

class OfficialMsgBV(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {

    init {
        itemView.debounceClick {
            support.simpleCallback?.invoke(bindingAdapterPosition)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        itemCell as OfficialMsgCell
        itemView.atv_title.text = itemCell.item.Title
        itemView.atv_content.text = itemCell.item.description
        itemView.atv_time.text = itemCell.item.PushTime.timeRule()
        if (itemCell.item.converImgUrl.isNullOrBlank())
            itemView.iv_pic.gone()
        else
            itemView.iv_pic.visible()

        support.imageLoader?.display(
            itemView.iv_pic,
            itemCell.item.converImgUrl,
            R.drawable.ic_sz_xf_error_large_4_3,
            R.drawable.ic_sz_xf_error_large_4_3
        )
    }
}