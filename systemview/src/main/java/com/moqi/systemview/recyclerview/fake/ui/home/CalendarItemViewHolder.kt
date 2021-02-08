package com.moqi.systemview.recyclerview.fake.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ItemVhCalendarBinding
import com.moqi.systemview.ext.GlideApp
import com.moqi.systemview.recyclerview.FreedomViewHolder
import com.moqi.systemview.recyclerview.VHProvider

/**
 *
 * created by reol at 2/8/21
 */
class CalendarItemViewHolder(parent: ViewGroup) : FreedomViewHolder<CalendarItem>(
    LayoutInflater.from(parent.context).inflate(R.layout.item_vh_calendar, parent, false)
) {

    private val vb = ItemVhCalendarBinding.bind(itemView)

    override fun onBindViewHolder() {
        vb.tvTitle.text = data.title
        vb.tvDate.text = data.displayTime
        vb.tvLabel.text = data.label
        vb.tvLabel.setLabelStyle(data.labelStatus)
        GlideApp.with(itemView.context)
            .load(data.iconUrl)
            .error(R.drawable.ic_jia)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(vb.ivIcon)
        data.action?.let {
            vb.root.setOnClickListener { it(data.calendarId) }
        }
    }

    override fun onRecycle() {

    }

    private fun TextView.setLabelStyle(labelStatus: Int) {
        when (labelStatus) {
            1 -> { // normal
                this.setTextColor(Color.parseColor("#333333"))
                this.setBackgroundColor(Color.parseColor("#E6E6E6"))
            }
            2 -> { // hot
                this.setTextColor(Color.parseColor("#FFB74D"))
                this.setBackgroundColor(Color.parseColor("#FFF3D9"))
            }
        }
    }

}

data class CalendarItem(
    val displayTime: String,
    val label: String,
    val labelStatus: Int,
    val title: String,
    val iconUrl: String,
    val calendarId: Int,
    val action: ((Int) -> Unit)? = null
) : VHProvider {
    override fun getViewType(): Int = 4
}
