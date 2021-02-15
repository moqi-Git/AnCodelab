package com.moqi.systemview.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moqi.systemview.recyclerview.fake.ui.home.*

/**
 *
 * created by reol at 2/7/21
 */
class FreedomAdapter(private val contentList: MutableList<VHProvider>) :
    RecyclerView.Adapter<FreedomViewHolder<VHProvider>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FreedomViewHolder<VHProvider> {
        val vh = FreedomViewHolderFactory.getViewHolder(viewType)(parent)
        requireNotNull(vh)
        return vh as FreedomViewHolder<VHProvider>
    }

    override fun onBindViewHolder(holder: FreedomViewHolder<VHProvider>, position: Int) {
        holder.bind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    override fun getItemViewType(position: Int): Int {
        return contentList[position].getViewType()
    }

    override fun onViewRecycled(holder: FreedomViewHolder<VHProvider>) {
        super.onViewRecycled(holder)
        holder.onRecycle()
    }

    fun setNewList(list: List<VHProvider>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}

abstract class FreedomViewHolder<DATA : VHProvider>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    protected lateinit var data: DATA

    fun bind(data: DATA) {
        this.data = data
        onBindViewHolder()
    }

    abstract fun onBindViewHolder()

    abstract fun onRecycle()
}

interface VHProvider {
    fun getViewType(): Int
}

object FreedomViewHolderFactory {

    private val map = HashMap<Int, (ViewGroup) -> FreedomViewHolder<out VHProvider>>()

    fun getViewHolder(viewType: Int): (ViewGroup) -> FreedomViewHolder<out VHProvider> {
        val builder = map[viewType] ?: throw IllegalStateException("未注册的 ViewType：$viewType")
        return builder
    }

    fun registerViewHolder(
        viewType: Int,
        builder: ((ViewGroup) -> FreedomViewHolder<out VHProvider>),
        forceReplace: Boolean = false
    ) {
        if (map[viewType] != null && !forceReplace){
            throw IllegalStateException("已注册过 ViewType：$viewType")
        }
        map[viewType] = builder
    }

    init {
        registerViewHolder(1, ::BannerViewHolder)
        registerViewHolder(2, ::CardTitleViewHolder)
        registerViewHolder(3, ::ImageShowViewHolder)
        registerViewHolder(4, ::CalendarItemViewHolder)
        registerViewHolder(5, ::PlayListViewHolder)

        //for ImageShowViewHolder
        registerViewHolder(1001, ::ImageShowItemViewHolder)
    }
}


