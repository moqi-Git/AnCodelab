package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemVhRankListBinding
import com.moqi.systemview.databinding.ItemVhRankListItemBinding
import com.moqi.systemview.ext.GlideApp
import com.moqi.systemview.recyclerview.FreedomAdapter
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/7/21
 */
class RankListViewHolder(parent: ViewGroup) : FreedomViewHolder<RankList>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_rank_list, parent, false)
) {
    private val vb = ItemVhRankListBinding.bind(itemView)
    private val adapter = FreedomAdapter(mutableListOf())

    override fun onBindViewHolder() {
        vb.rvRank.layoutManager = LinearLayoutManager(itemView.context)
        vb.rvRank.adapter = adapter

        vb.rvRank.postDelayed({
            adapter.setNewList(data.content)
        }, 3000)
    }

    override fun onRecycle() {
        //
    }

}

class RankListItemViewHolder(parent: ViewGroup) : FreedomViewHolder<RankListItem>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_rank_list_item, parent, false)
) {
    private val vb = ItemVhRankListItemBinding.bind(itemView)

    override fun onBindViewHolder() {
        vb.tvTitle.text = data.title
        vb.tvRankNumber.text = adapterPosition.toString()
        vb.tvDescription.text = data.description
        GlideApp.with(vb.root)
            .load(data.iconUrl)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(vb.ivCover)
    }

    override fun onRecycle() {

    }
}

data class RankList(
    val content: List<RankListItem>
) : VHProvider {
    override fun getViewType(): Int = 6
}

data class RankListItem(
    val title: String,
    val iconUrl: String,
    val description: String,
    val authorId: Int,
    val authorName: String,
    val lable: String = "",
    val authorAction: ((authorId: Int) -> Unit)? = null
) : VHProvider {
    override fun getViewType(): Int {
        return 1002
    }
}