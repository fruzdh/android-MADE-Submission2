package com.example.core.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

class CenterZoomLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        val mShrinkAmount = 0.5f
        val mShrinkDistance = 0.75f

        val midpoint = width / 2.0f
        val d0 = 0.0f
        val d1 = mShrinkDistance * midpoint
        val s0 = 1.0f
        val s1 = 1.0f - mShrinkAmount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child != null) {
                val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.0f
                val d = min(d1, abs(midpoint - childMidpoint))
                val scale = s0 + (s1-s0) * (d-d0) / (d1-d0)
                child.scaleX = scale
                child.scaleY = scale
            }
        }
        return scrolled
    }
}