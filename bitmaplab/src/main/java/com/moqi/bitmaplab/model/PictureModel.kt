package com.moqi.bitmaplab.model

import com.moqi.bitmaplab.R
import java.net.URL

data class PictureModel(
    val type: Int,
    val name: String,
    val path: String = "",
    val resId: Int = 0
)

class DefaultConfig{
    companion object{
        @JvmField
        val ASSETS = arrayListOf(
            PictureModel(1, "arknights1.jpg", "file:///android_asset/arknights1.jpg")
        )

        @JvmField
        val DRAWABLE = arrayListOf(
            PictureModel(11, "arknights1.jpg", resId = R.drawable.arknights1)
        )

        @JvmField
        val URL = arrayListOf(
            PictureModel(100, "arknights1", "https://")
        )
    }
}