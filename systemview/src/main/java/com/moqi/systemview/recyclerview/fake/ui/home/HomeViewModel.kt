package com.moqi.systemview.recyclerview.fake.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moqi.systemview.recyclerview.VHProvider

class HomeViewModel : ViewModel() {

    val contentData = MutableLiveData<List<VHProvider>>()

    private val host = "http://192.168.0.104:8080"

    init {
        contentData.value = arrayListOf(
            Banner(listOf(
                BannerItem("首发", "$host/fake/banner1.jpg"),
                BannerItem("新图", "$host/fake/banner2.jpg"),
                BannerItem("独家", "$host/fake/banner3.jpg"),
            )),
        )
    }
}