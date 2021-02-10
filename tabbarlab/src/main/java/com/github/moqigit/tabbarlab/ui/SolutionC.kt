package com.github.moqigit.tabbarlab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.moqigit.tabbarlab.R
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentSolutionCBinding
import com.github.moqigit.tabbarlab.databinding.ItemBannerBinding

/**
 *
 * created by reol at 1/5/21
 */
class SolutionC : BaseFragment<FragmentSolutionCBinding>() {
    override fun getName(): String {
        return "SolutionC"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSolutionCBinding {
        return FragmentSolutionCBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BannerAdapter(arrayListOf<Int>())
//        val list = listOf(
//            R.drawable.pic_banner1,
//            R.drawable.pic_banner2,
//            R.drawable.pic_banner3,
//        ).map {
//            LayoutInflater.from(requireContext()).inflate(R.layout.item_banner, null, false).apply {
//                findViewById<ImageView>(R.id.iv_banner).setImageResource(it)
//            }
//        }
//        val adapter = BannerVPAdapter1(list)
        vb.vpBanner.adapter = adapter
    }

    class BannerAdapter(private val bannerList: MutableList<Int>) :
        RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {


        class BannerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        ) {
            private var vb: ItemBannerBinding = ItemBannerBinding.bind(itemView)

            fun bindView(resId: Int) {
                vb.ivBanner.setImageResource(resId)
                vb.tvTitle.text = "Title $resId"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            return BannerViewHolder(parent)
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            holder.bindView(bannerList[position])
        }

        override fun getItemCount(): Int = bannerList.size

        fun setNewList(list: List<Int>) {
            bannerList.clear()
            bannerList.addAll(list)
            notifyDataSetChanged()
        }
    }
}

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