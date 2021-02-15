package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemVhImageDetailBinding
import com.moqi.systemview.databinding.ItemVhImageShowBinding
import com.moqi.systemview.ext.GlideApp
import com.moqi.systemview.recyclerview.FreedomAdapter
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/7/21
 */
class ImageShowViewHolder(parent: ViewGroup) : FreedomViewHolder<ImageList>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_image_show, parent, false)
) {
    private val vb = ItemVhImageShowBinding.bind(itemView)
    private val adapter = FreedomAdapter(mutableListOf())

    init {
        vb.rvImageShow.adapter = adapter
        vb.rvImageShow.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

//        vb.rvImageShow.layoutManager = FanLayoutManager(itemView.context)
        vb.rvImageShow.addItemDecoration(ImageShowDecoration())
    }

    override fun onBindViewHolder() {
        adapter.setNewList(data.content)
    }

    override fun onRecycle() {

    }
}

class ImageShowItemViewHolder(parent: ViewGroup) : FreedomViewHolder<ImageListItem>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_image_detail, parent, false)
) {
    private val vb = ItemVhImageDetailBinding.bind(itemView)

    override fun onBindViewHolder() {
        vb.tvTitle.text = data.title
        vb.tvViewCount.text = data.pageViewCount
        GlideApp.with(itemView.context)
            .load(data.coverUrl)
            .error(R.drawable.ic_jia)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(vb.ivCover)
        data.action?.let {
            vb.root.setOnClickListener { it(data.imageId) }
        }
    }

    override fun onRecycle() {
    }
}

data class ImageList(
    val content: List<ImageListItem>,
) : VHProvider {
    override fun getViewType(): Int = 3
}

data class ImageListItem(
    val imageId: Int,
    val title: String,
    val coverUrl: String,
    val pageViewCount: String,
    val action: ((imageId: Int) -> Unit)? = null
) : VHProvider {
    override fun getViewType(): Int = 1001
}