package com.moqi.opengl

import android.app.Application
import android.content.Context

/**
 *
 * created by reol at 1/23/21
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object{
        private var appContext: Context? = null

        @JvmStatic
        fun requireContext(): Context{
            return appContext!!
        }
    }
}