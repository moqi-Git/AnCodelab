package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemVhCardTitleBinding
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/7/21
 */
class CardTitleViewHolder(parent: ViewGroup) : FreedomViewHolder<CardTitle>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_card_title, parent, false)
) {
    private val vb = ItemVhCardTitleBinding.bind(itemView)

    override fun onBindViewHolder() {
        vb.tvTitle.text = data.name
        vb.tvAction.text = data.actionName
        data.action?.let {
            vb.tvAction.setOnClickListener { it() }
        }
    }

    override fun onRecycle() {
        //
    }
}

data class CardTitle(
    val name: String,
    val actionName: String,
    val action: (() -> Unit)? = null,
) : VHProvider {
    override fun getViewType(): Int = 2
}