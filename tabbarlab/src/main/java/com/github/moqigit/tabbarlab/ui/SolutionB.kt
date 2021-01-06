package com.github.moqigit.tabbarlab.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.moqigit.tabbarlab.R
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentSolutionBBinding
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 *
 * created by reol at 1/5/21
 */
class SolutionB: BaseFragment<FragmentSolutionBBinding>() {

    private val tabList = arrayListOf("春", "夏", "秋", "冬")

    override fun getName(): String {
        return "SolutionB"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSolutionBBinding {
        return FragmentSolutionBBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb.sbTabLayout1.apply {
            navigator = CommonNavigator(requireContext()).apply {
                this.isAdjustMode = true
                this.isSmoothScroll = true
                this.isFollowTouch = true
                adapter = object: CommonNavigatorAdapter(){
                    override fun getCount(): Int {
                        return tabList.size
                    }

                    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                        val title = SimplePagerTitleView(requireContext()).apply {
                            this.selectedColor = resources.getColor(R.color.teal_700)
                            this.normalColor = resources.getColor(R.color.purple_500)
                            this.text = tabList[index]
                            this.setOnClickListener {
                                vb.sbViewpager.currentItem = index
                            }
                        }
                        return title
                    }

                    override fun getIndicator(context: Context?): IPagerIndicator {
                        val indicator = LinePagerIndicator(requireContext()).apply {
                            mode = LinePagerIndicator.MODE_MATCH_EDGE
                            lineHeight = 4f
                            setColors(resources.getColor(R.color.teal_700))
                        }
                        return indicator
                    }

                }
            }
        }

        vb.sbViewpager.adapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount(): Int {
                return tabList.size
            }

            override fun createFragment(position: Int): Fragment {
                val tsf = TextSampleFragment().apply {
                    arguments = Bundle().apply {
                        putString("text", position.toString())
                    }
                }
                return tsf
            }
        }

        vb.sbViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                vb.sbTabLayout1.onPageSelected(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                vb.sbTabLayout1.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                vb.sbTabLayout1.onPageScrollStateChanged(state)
            }
        })
    }

}