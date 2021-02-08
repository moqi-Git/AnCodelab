package com.moqi.systemview.recyclerview.fake.ui.home

import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/8/21
 */
//class PagerShowViewHolder(parent: ViewGroup): FreedomViewHolder<PagerData>(
//    LayoutInflater.from(parent.context).inflate()
//){
//
//
//}

data class PagerData(
    val content: List<List<PagerListItem>>
) : VHProvider {
    override fun getViewType(): Int = 5
}

data class PagerListItem(
    val title: String,
    val iconUrl: String,
    val authorName: String
) : VHProvider {
    override fun getViewType(): Int = 1002
}