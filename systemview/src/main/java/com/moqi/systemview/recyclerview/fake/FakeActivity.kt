package com.moqi.systemview.recyclerview.fake

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ActivityFakeBinding
import com.moqi.systemview.recyclerview.fake.ui.home.HomeFragment
import com.moqi.systemview.recyclerview.fake.ui.notifications.NotificationsFragment
import com.moqi.systemview.recyclerview.fake.ui.timeline.TimelineFragment

class FakeActivity : AppCompatActivity() {

    private lateinit var vb: ActivityFakeBinding

    private val fragments = listOf(
        ::HomeFragment,
        ::TimelineFragment,
        ::NotificationsFragment,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityFakeBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.navView.setOnNavigationItemSelectedListener {
            vb.vpMain.setCurrentItem(it.position(), true)
            return@setOnNavigationItemSelectedListener true
        }

        vb.vpMain.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]()
        }
        vb.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val id = when(position){
                    0 -> R.id.navigation_home
                    1 -> R.id.navigation_dashboard
                    2 -> R.id.navigation_notifications
                    else -> throw IllegalArgumentException("不存在的 MenuItem")
                }
                vb.navView.selectedItemId = id
            }
        })
    }

    private fun MenuItem.position(): Int{
        return when(this.itemId){
            R.id.navigation_home -> 0
            R.id.navigation_dashboard -> 1
            R.id.navigation_notifications -> 2
            else -> throw IllegalArgumentException(
                "MenuItem 未注册到 ViewPager"
            )
        }
    }
}