package com.moqi.systemview.recyclerview.fake.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemBannerPagerBinding
import com.moqi.systemview.databinding.ItemVhBannerBinding
import com.moqi.systemview.ext.GlideApp
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/7/21
 */
class BannerViewHolder(parent: ViewGroup) : FreedomViewHolder<Banner>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_banner, parent, false)
) {
    private val binding: ItemVhBannerBinding = ItemVhBannerBinding.bind(itemView)
    private val adapter = BannerPagerAdapter(mutableListOf())
//    private val mViews = mutableListOf<View>()

    init {
        binding.vpBanner.adapter = adapter
    }

    override fun onBindViewHolder() {
        adapter.setNewList(data.bannerList)
//        for (i in data.bannerList) {
//            val v = LayoutInflater.from(itemView.context).inflate(R.layout.item_banner_pager, null, false)
//            val iv = v.findViewById<ImageView>(R.id.iv_content)
//            GlideApp.with(itemView.context)
//                .load(i.imageUrl)
//                .transform(CenterCrop(), RoundedCorners(10))
//                .into(iv)
//            mViews.add(v)
//        }
//        binding.vpBanner.adapter = BannerVPAdapter1(mViews)
    }

    override fun onRecycle() {
        //
    }
}

class BannerPagerAdapter(private val mList: MutableList<BannerItem>) :
    RecyclerView.Adapter<BannerPagerAdapter.BannerPagerHolder>() {

    class BannerPagerHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_banner_pager, parent, false)
    ) {
        private val vb = ItemBannerPagerBinding.bind(itemView)

        fun bind(data: BannerItem) {
            GlideApp.with(itemView.context)
                .load(data.imageUrl)
                .error(R.drawable.ic_jia)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(vb.ivContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPagerHolder {
        return BannerPagerHolder(parent)
    }

    override fun onBindViewHolder(holder: BannerPagerHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    fun setNewList(list: List<BannerItem>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }
}

data class Banner(
    val bannerList: List<BannerItem>
) : VHProvider {
    override fun getViewType(): Int = 1
}

data class BannerItem(
    val label: String,
    val imageUrl: String
)

//class BannerVPAdapter1(private val views: List<View>): PagerAdapter(){
//    override fun getCount(): Int {
//        return views.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
////        return super.instantiateItem(container, position)
//        container.addView(views[position])
//        return views[position]
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
////        super.destroyItem(container, position, `object`)
//        container.removeView(views[position])
//    }
//
//}