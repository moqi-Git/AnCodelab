package com.moqi.merrychristmas

import android.app.Application
import android.content.Context

/**
 * @author zhangpan
 * @date 2020/12/24
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
            private set
    }
}