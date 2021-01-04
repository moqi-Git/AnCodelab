package com.moqi.systemview.ext

import android.view.View

fun View.toLogInfo(): String{
    return "view:${this}=> size=($width,$height);"
}