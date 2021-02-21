package com.moqi.systemview.recyclerview.fake.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moqi.systemview.recyclerview.VHProvider

class HomeViewModel : ViewModel() {

    val contentData = MutableLiveData<List<VHProvider>>()

    private val host = "http://172.17.0.93:8080"
    private val path = "open"  // fake open

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
                    ImageListItem(2, "二哈拆家", "$host/$path/cover3.jpg", "1", ::clickImageCover),
                    ImageListItem(
                        3,
                        "格子|黑白纷争",
                        "$host/$path/cover4.jpg",
                        "36456",
                        ::clickImageCover
                    ),
                    ImageListItem(4, "黑色玫瑰", "$host/$path/cover5.jpg", "1000", ::clickImageCover),
                    ImageListItem(5, "你想听一下吗", "$host/$path/cover6.jpg", "443", ::clickImageCover),
                )
            ),
            CardTitle(
                "推荐视频",
                "更多",
                ::clickRecommend,
            ),
            CardTitle(
                "推荐播单",
                "更多",
                ::clickRecommend,
            ),
            CardTitle(
                "作者排行",
                "查看全部",
                ::clickRecommend,
            ),
            RankList(
                arrayListOf(
                    RankListItem("首当其冲", "$host/$path/cover1.jpg", "人为什么要上班", 1, "who"),
                    RankListItem("不想上班怎么办", "$host/$path/cover2.jpg", "退休后的美好生活", 2, "who"),
                    RankListItem("好困啊", "$host/$path/cover3.jpg", "人为什么不睡觉", 3, "who"),
                )
            ),
            CardTitle(
                "新作预告",
                "预告日历",
                ::clickRecommend,
            ),
            CalendarItem(
                "今天",
                "热点",
                2,
                "【进入游戏】你有想守护的人吗",
                "$host/$path/cover5.jpg",
                1,
                ::clickImageCover
            ),
            CalendarItem(
                "明天",
                "预告",
                1,
                "鸽鸽的新作一定发售",
                "$host/$path/banner2.jpg",
                1,
                ::clickImageCover
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