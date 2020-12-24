package com.moqi.merrychristmas.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.moqi.merrychristmas.App
import com.moqi.merrychristmas.R


class TreeWidgetProvider : AppWidgetProvider() {
    private var lightOnIntent: PendingIntent? = null
    private var lightOffIntent: PendingIntent? = null
    private var remoteTree: RemoteViews? = null
    private val ACTION_REFRESH = "com.moqi.merrychristmas.tree.ACTION_REFRESH"
    private val UPDATE_DURATION = 500L
    private val handler: Handler = Handler(Looper.getMainLooper())

    private var i: Int = 0
    private val mRunnable = Runnable { changeColor() }

    private fun changeColor() {
        if (i % 2 == 0) {
            remoteTree?.setViewVisibility(R.id.remote_iv_tree_light, View.VISIBLE)
        } else {
            remoteTree?.setViewVisibility(R.id.remote_iv_tree_light, View.GONE)
        }
        i = (i + 1) % 2
        val random = Math.random()
        when {
            random < 0.45 -> {
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_1, View.VISIBLE)
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_2, View.GONE)
            }
            random > 0.9 -> {
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_1, View.VISIBLE)
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_2, View.VISIBLE)
            }
            else -> {
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_1, View.GONE)
                remoteTree?.setViewVisibility(R.id.remote_iv_tree_stars_2, View.VISIBLE)
            }
        }
        AppWidgetManager.getInstance(App.context).updateAppWidget(
            ComponentName(App.context, TreeWidgetProvider::class.java),
            remoteTree
        )

        handler.postDelayed(mRunnable, UPDATE_DURATION)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.e(
            "asdfg",
            "onReceive ${intent?.action}，bundle=${intent?.extras?.keySet()?.contains("light")}"
        )
        if (intent != null) {
            when (intent.action) {
                ACTION_REFRESH -> {
                    remoteTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
                    handler.removeCallbacks(mRunnable)
                    handler.postDelayed(mRunnable, UPDATE_DURATION)
                }
            }
        }
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.e("asdfg", "onUpdate")

        val lightOn = Intent().apply {
            setAction(ACTION_REFRESH)
            setComponent(ComponentName(context!!, TreeWidgetProvider::class.java))
            putExtra("light", true)
        }
        val lightOff = Intent().apply {
            setAction(ACTION_REFRESH)
            setComponent(ComponentName(context!!, TreeWidgetProvider::class.java))
            putExtra("light", false)
        }
        lightOnIntent =
            PendingIntent.getBroadcast(context, 1000, lightOn, PendingIntent.FLAG_UPDATE_CURRENT)
        lightOffIntent =
            PendingIntent.getBroadcast(context, 1001, lightOff, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
//        remoteTree?.setOnClickPendingIntent(R.id.remote_iv_tree, lightOnIntent)
//        remoteTree?.setOnClickPendingIntent(R.id.remote_iv_tree_light, lightOffIntent)
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetIds, remoteTree)
    }


    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        Log.e("asdfg", "onAppWidgetOptionsChanged")
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        Log.e("asdfg", "onDeleted")
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.e("asdfg", "onEnabled")
        remoteTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
        handler.removeCallbacks(mRunnable)
        handler.postDelayed(mRunnable, UPDATE_DURATION)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.e("asdfg", "onDisabled")
        handler.removeCallbacks(mRunnable)
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
        Log.e("asdfg", "onRestored")
    }
}