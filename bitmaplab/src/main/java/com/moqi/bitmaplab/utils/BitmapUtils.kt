package com.moqi.bitmaplab.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

fun Bitmap.logInfo(): String{
    val pixelFormat = this.config.name
    val memorySize = allocationByteCount
    return "size=($width,$height);\npixelFormat=$pixelFormat;\nmemorySize=$memorySize(${memorySize.div(1024)}KB)"
}

fun loadBitmapFromAssets(context: Context, name: String): Bitmap?{
    val assetManager = context.assets
    try {
        val pictureStream = assetManager.open(name)
        val bitmap = BitmapFactory.decodeStream(pictureStream)
        return bitmap
    } catch (e: IOException){
        e.printStackTrace()
        return null
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
fun loadBitmapFromDrawable(context: Context, resId: Int): Bitmap?{
    return BitmapFactory.decodeResource(context.resources, resId)
}