package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.moqi.systemview.R
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/7/21
 */
class RankListViewHolder(parent: ViewGroup) : FreedomViewHolder<RankList>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_rank_list, parent, false)
) {
    override fun onBindViewHolder() {

    }

    override fun onRecycle() {
        //
    }

}

data class RankList(
    val content: List<RankListItem>
) : VHProvider {
    override fun getViewType(): Int = 3
}

data class RankListItem(
    val title: String,
    val iconUrl: String,
    val description: String,
    val authorId: Int,
    val authorName: String,
    val lable: String = "",
    val authorAction: ((authorId: Int) -> Unit)? = null
)