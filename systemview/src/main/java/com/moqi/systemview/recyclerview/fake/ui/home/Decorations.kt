package com.moqi.systemview.recyclerview.fake.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moqi.systemview.R

/**
 *
 * created by reol at 2/7/21
 */

class ImageShowDecoration(
//    private val normal: Int,
//    private val special: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val special = view.context.resources.getDimension(R.dimen.fake_left_margin).toInt()
        val normal = view.context.resources.getDimension(R.dimen.fake_6dp).toInt()

        val position = parent.getChildLayoutPosition(view)
        val last = parent.adapter?.itemCount
        if (position == 0) {
            outRect.left = special
        } else {
            outRect.left = normal
        }
        if (position + 1 == last) {
            outRect.right = special
        } else {
            outRect.right = normal
        }
    }

}