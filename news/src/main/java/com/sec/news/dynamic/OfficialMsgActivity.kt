package com.sec.news.dynamic

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.sec.common.BaseActivity
import com.sec.common.adapter.ItemCell
import com.sec.common.adapter.RecyclerAdapter
import com.sec.common.adapter.createAdapter
import com.sec.common.constant.MMKVConst
import com.sec.common.constant.RoutePath
import com.sec.common.handle
import com.sec.common.ktx.*
import com.sec.common.manager.ImageLoader
import com.sec.news.R
import com.sec.news.dynamic.cell.OfficialMsgCell
import com.sec.news.dynamic.cell.OfficialMsgErrorCell
import com.sec.news.dynamic.vm.OfficialMsgViewModel
import kotlinx.android.synthetic.main.activity_official_msg_news.*

@Route(path = RoutePath.DYNAMIC_SYSTEM_MSG)
class OfficialMsgActivity : BaseActivity() {
    private val pageCount = 10
    private val viewModels = viewModels<OfficialMsgViewModel>()
    private lateinit var mAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_official_msg_news)
        initView()
        initAdapter()
        initData()
    }

    private fun initView() {
        toolbar("官方消息")
        smartRefreshLayout.autoRefresh()

        smartRefreshLayout.onSmartRefreshCallback {
            onRefresh {
                viewModels.value.pagerIndex = 0
                getList(0)
            }
            onLoadMore {
                getList(viewModels.value.pagerIndex * pageCount)
            }
        }
    }

    private fun initAdapter() {
        mAdapter = createAdapter {
            imageLoader = ImageLoader(this@OfficialMsgActivity)
            onSimpleCallback {
                mAdapter.currentList[it] as OfficialMsgCell
                //
            }
            retry = {
                smartRefreshLayout.autoRefresh()
            }
        }

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider_dynamic_list_item)?.let {
            decoration.setDrawable(
                it
            )
        }
        recyclerView.apply {
            adapter = mAdapter
            addItemDecoration(decoration)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initData() {
        viewModels.value.mOfficialNewsJson.handle(this) {
            onSuccess {
                viewModels.value.pagerIndex++
                mAdapter.apply {
                    val itemList = mutableListOf<ItemCell>()
                    it.forEach { data ->
                        val item = OfficialMsgCell(data)
                        itemList.add(item)
                    }
                    submitList((viewModels.value.pagerIndex), itemList)
                }
            }
            onFail {
                mAdapter.submitList(1, listOf(OfficialMsgErrorCell(it)))
            }
            smartRefreshLayout.xfLoadComplete(
                viewModels.value.pagerIndex * pageCount
                , viewModels.value.total, pageCount
            )
        }
    }

    private fun getList(pageIndex: Int) {
        val param: String =
            jsonOf(
                "StartIndex" to pageIndex,
                "Length" to pageCount,
                "isNew" to 1,
                "SendItems" to "U108033335"
            )
        viewModels.value.getOfficialNewsList(param)
    }
}
