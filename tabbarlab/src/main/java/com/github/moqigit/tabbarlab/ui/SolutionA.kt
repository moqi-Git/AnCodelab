package com.github.moqigit.tabbarlab.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentContentTextBinding
import com.github.moqigit.tabbarlab.databinding.FragmentSolutionABinding
import com.google.android.material.tabs.TabLayout

/**
 *
 * created by reol at 1/5/21
 */
class SolutionA : BaseFragment<FragmentSolutionABinding>() {

    private val contentSize = 4

    override fun getName(): String {
        return "SolutionA"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSolutionABinding {
        return FragmentSolutionABinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.saTabLayout1.selectTab(vb.saTabLayout1.getTabAt(0))
        vb.saTabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { vb.saViewpager.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        vb.saViewpager.adapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount(): Int {
                return contentSize
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
        vb.saViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                vb.saTabLayout1.selectTab(vb.saTabLayout1.getTabAt(position))
            }
        })
    }

}