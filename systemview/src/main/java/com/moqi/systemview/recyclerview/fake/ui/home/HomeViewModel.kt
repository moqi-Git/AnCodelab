package com.moqi.systemview.recyclerview.fake.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moqi.systemview.recyclerview.VHProvider

class HomeViewModel : ViewModel() {

    val contentData = MutableLiveData<List<VHProvider>>()

    private val host = "http://192.168.0.104:8080"
    private val path = "fake"

    init {
        contentData.value = arrayListOf(
            Banner(
                listOf(
                    BannerItem("首发", "$host/$path/banner1.jpg"),
                    BannerItem("新图", "$host/$path/banner2.jpg"),
                    BannerItem("独家", "$host/$path/banner3.jpg"),
                )
            ),
            CardTitle(
                "推荐图库",
                "更多",
                ::clickRecommend,
            ),
            ImageList(
                arrayListOf(
                    ImageListItem(0, "一期一会", "$host/$path/cover1.jpg", "100", ::clickImageCover),
                    ImageListItem(
                        1,
                        "仿真机器人会梦到电子狗吗",
                        "$host/$path/cover2.jpg",
                        "10万",
                        ::clickImageCover
                    ),
                    ImageListItem(2, "二哈拆家", "$host/$path/cover3.jpg", "100", ::clickImageCover),
                    ImageListItem(3, "格子|黑白纷争", "$host/$path/cover4.jpg", "100", ::clickImageCover),
                    ImageListItem(4, "黑色玫瑰", "$host/$path/cover5.jpg", "100", ::clickImageCover),
                    ImageListItem(5, "你想尝一下吗", "$host/$path/cover6.jpg", "100", ::clickImageCover),
                )
            ),
            CardTitle(
                "推荐视频",
                "更多",
                ::clickRecommend,
            ),
            CardTitle(
                "作者排行",
                "查看全部",
                ::clickRecommend,
            ),
            CardTitle(
                "新作预告",
                "预告日历",
                ::clickRecommend,
            ),
        )
    }

    private fun clickRecommend() {
        Log.e("asdfg", "clickRecommend")
    }

    private fun clickImageCover(imageId: Int) {
        Log.e("asdfg", "clickImageCover: $imageId")
    }
}