package com.sec.news.dynamic.cell

import android.view.View
import com.sec.common.adapter.ItemCell
import com.sec.common.adapter.RecyclerSupport
import com.sec.common.adapter.RecyclerVH
import com.sec.common.ktx.debounceClick
import com.sec.common.network.ERROR_NET
import com.sec.news.R
import kotlinx.android.synthetic.main.item_official_msg_empty_error_news.view.*

/**
 * 错误页面
 */
class OfficialMsgErrorCell(val errorCode: Int) : ItemCell {

    override fun layoutResId(): Int = R.layout.item_official_msg_empty_error_news

    override fun itemId(): String = "$errorCode"

    override fun itemContent() = errorCode.toString()

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport): RecyclerVH =
        OfficialMsgErrorVH(itemView, support)
}

class OfficialMsgErrorVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {

    init {
        itemView.debounceClick {
            support.retry?.invoke()
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)

        itemCell as OfficialMsgErrorCell

        when (itemCell.errorCode) {
            ERROR_NET -> {
                itemView.ic_img.setImageResource(R.drawable.ic_app_error)
                itemView.tv_title.text = itemView.context.getString(R.string.list_net_error)
            }
            else -> {
                itemView.ic_img.setImageResource(R.drawable.ic_carton_message_empty)
                itemView.tv_title.text = itemView.context.getString(R.string.empty_news)
            }
        }
    }
}