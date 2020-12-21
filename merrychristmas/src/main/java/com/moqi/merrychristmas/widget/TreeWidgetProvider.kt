package com.moqi.merrychristmas.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.moqi.merrychristmas.R

class TreeWidgetProvider: AppWidgetProvider() {

    private val ACTION_REFRESH = "com.moqi.merrychristmas.tree.ACTION_REFRESH"


    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.e("asdfg", "onReceive ${intent?.action}，bundle=${intent?.extras?.keySet()?.contains("light")}")
        if (intent != null){
            val action = intent.action
            when(action){
                ACTION_REFRESH -> {
                    val lightOn = intent.getBooleanExtra("light", false)
//                    Log.e("asdfg", "lightOn = $lightOn")
                    val remoteTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
                    if (lightOn){
                        remoteTree.setViewVisibility(R.id.remote_iv_tree_light, View.VISIBLE)
                    } else {
                        remoteTree.setViewVisibility(R.id.remote_iv_tree_light, View.GONE)
                    }

                    AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, TreeWidgetProvider::class.java), remoteTree)
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
        val lightOnIntent = PendingIntent.getBroadcast(context, 1000, lightOn, PendingIntent.FLAG_UPDATE_CURRENT)
        val lightOffIntent = PendingIntent.getBroadcast(context, 1001, lightOff, PendingIntent.FLAG_UPDATE_CURRENT)
        val remoteTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
        remoteTree.setOnClickPendingIntent(R.id.remote_iv_tree, lightOnIntent)
        remoteTree.setOnClickPendingIntent(R.id.remote_iv_tree_light, lightOffIntent)
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
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.e("asdfg", "onDisabled")
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
        Log.e("asdfg", "onRestored")
    }
}