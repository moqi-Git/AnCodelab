package com.moqi.bitmaplab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.moqi.bitmaplab.databinding.ActivityMainBinding
import com.moqi.bitmaplab.model.DefaultConfig
import com.moqi.bitmaplab.model.PictureModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding

    private val historyAdapter = HistoryAdapter()
    private val historyList = ArrayList<PictureModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        //todo: 获取historyList
        historyList.addAll(DefaultConfig.ASSETS)
        historyList.addAll(DefaultConfig.DRAWABLE)
        historyAdapter.apply {
            onItemClickListener = {model ->
                openPicture(model)
            }
            onItemLongClickListener = {model ->
                // 长按操作再考虑一下
            }
            updateContentList(historyList)
        }
        vb.rvHistory.adapter = historyAdapter
        vb.rvHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun openPicture(model: PictureModel){
        openPicture {
            putInt("type", model.type)
            putString("path", model.path)
            putString("name", model.name)
            putInt("resId", model.resId)
        }
    }

    private fun openPicture(param: (Bundle.() -> Unit)){
        val intent = Intent(this, PictureActivity::class.java)
        val b = Bundle()
        param(b)
        intent.putExtras(b)
        startActivity(intent)
    }
}