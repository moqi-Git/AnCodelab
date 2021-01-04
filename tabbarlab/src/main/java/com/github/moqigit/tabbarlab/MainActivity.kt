package com.github.moqigit.tabbarlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.moqigit.tabbarlab.databinding.ActivityMainBinding
import com.github.moqigit.tabbarlab.ui.SolutionA
import com.github.moqigit.tabbarlab.ui.SolutionB
import com.github.moqigit.tabbarlab.ui.SolutionC

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.mainBnv.setOnNavigationItemSelectedListener {
            selectSolutionById(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }
        vb.mainBnv.setOnNavigationItemReselectedListener {
            // refresh content fragment
        }
        vb.mainBnv.selectedItemId = R.id.navi_item_1
        selectSolutionById(R.id.navi_item_1)
    }

    private fun selectSolutionById(itemId: Int) {
        val fragment =
            when (itemId) {
                R.id.navi_item_1 -> {
                    SolutionA()
                }
                R.id.navi_item_2 -> {
                    SolutionB()
                }
                R.id.navi_item_3 -> {
                    SolutionC()
                }
                R.id.navi_item_4 -> {
                    SolutionC()
                }
                else -> {
                    SolutionC()
                }
            }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.tag)
            .disallowAddToBackStack()
            .commit()
    }


}