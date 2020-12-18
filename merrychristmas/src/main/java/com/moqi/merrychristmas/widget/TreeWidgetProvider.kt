package com.moqi.merrychristmas.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import com.moqi.merrychristmas.MainActivity
import com.moqi.merrychristmas.R

class TreeWidgetProvider: AppWidgetProvider() {

    private val ACTION_REFRESH = "com.moqi.merrychristmas.tree.ACTION_REFRESH"

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.e("asdfg", "onReceive ${intent?.action}")
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.e("asdfg", "onUpdate")

        val ri = Intent().apply {
            setAction(ACTION_REFRESH)
            setComponent(ComponentName(context!!, TreeWidgetProvider::class.java))
        }
        val refreshIntent = PendingIntent.getBroadcast(context, 1000, ri, PendingIntent.FLAG_UPDATE_CURRENT)
        val ci = Intent().apply {
            setPackage(context!!.packageName)
            setClass(context, MainActivity::class.java)
        }
        val configIntent = PendingIntent.getActivity(context, 1001, ci, PendingIntent.FLAG_UPDATE_CURRENT)
        val ivTree = RemoteViews(context!!.packageName, R.layout.widget_tree)
        ivTree.setOnClickPendingIntent(R.id.remote_iv_refresh, refreshIntent)
        ivTree.setOnClickPendingIntent(R.id.remote_iv_tree, configIntent)
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetIds, ivTree)
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
        if (context == null){
            return
        }
        Log.e("asdfg", "onEnabled confirmed")

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