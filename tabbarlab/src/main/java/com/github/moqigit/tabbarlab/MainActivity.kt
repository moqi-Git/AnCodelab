package com.github.moqigit.tabbarlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.github.moqigit.tabbarlab.databinding.ActivityMainBinding
import com.github.moqigit.tabbarlab.ui.SolutionA
import com.github.moqigit.tabbarlab.ui.SolutionB
import com.github.moqigit.tabbarlab.ui.SolutionC
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val mTabIds = listOf(
            R.id.navi_item_1,
            R.id.navi_item_2,
            R.id.navi_item_3,
//            R.id.navi_item_4,
        )

        vb.mainBnv.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        vb.mainBnv.setOnNavigationItemSelectedListener {
//            selectSolutionById()
            vb.mainViewPager.setCurrentItem(mTabIds.indexOf(it.itemId), true)
            return@setOnNavigationItemSelectedListener true
        }
        vb.mainBnv.setOnNavigationItemReselectedListener {
            // refresh content fragment
        }
        vb.mainBnv.selectedItemId = R.id.navi_item_1
//        selectSolutionById(R.id.navi_item_1)

        val mFragments = listOf(
            ::SolutionA,
            ::SolutionB,
            ::SolutionC,
//            ::SolutionC,
        )

        vb.mainViewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int = mFragments.size

            override fun getItem(position: Int): Fragment {
                return mFragments[position]()
            }
        }
        vb.mainViewPager.setCurrentItem(0, false)
        vb.mainViewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                vb.mainBnv.selectedItemId = mTabIds[position]
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

//    private fun selectSolutionById(itemId: Int) {
//        val fragment =
//            when (itemId) {
//                R.id.navi_item_1 -> {
//                    SolutionA()
//                }
//                R.id.navi_item_2 -> {
//                    SolutionB()
//                }
//                R.id.navi_item_3 -> {
//                    SolutionC()
//                }
//                R.id.navi_item_4 -> {
//                    SolutionC()
//                }
//                else -> {
//                    SolutionC()
//                }
//            }
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, fragment, fragment.tag)
//            .disallowAddToBackStack()
//            .commit()
//    }


}