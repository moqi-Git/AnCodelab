package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cleveroad.fanlayoutmanager.FanLayoutManager
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemVhPlaylistBinding
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/12/21
 */
class PlayListViewHolder(parent: ViewGroup) : FreedomViewHolder<PlayList>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_playlist, parent, false)
) {
    private val vb = ItemVhPlaylistBinding.bind(itemView)

    override fun onBindViewHolder() {
        vb.rvPlayList.layoutManager = FanLayoutManager(itemView.context)
    }

    override fun onRecycle() {

    }
}

data class PlayList(
    val content: List<PlayListItem>
) : VHProvider {
    override fun getViewType(): Int = 5
}

data class PlayListItem(
    val coverUrl: String
)