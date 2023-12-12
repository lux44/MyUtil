package com.example.myutil.utils

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalMarginItemDecoration(firstEndGap: Float, sideGap: Float, context: Context) :
    RecyclerView.ItemDecoration() {

    private val firstLastGap: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        firstEndGap,
        context.resources.displayMetrics
    ).toInt()
    private val sideGap: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        sideGap,
        context.resources.displayMetrics
    ).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {

        val itemPostion = parent.getChildAdapterPosition(view)
        if (itemPostion == RecyclerView.NO_POSITION) return
        val topOffset = if (isFirstItem(itemPostion)) firstLastGap else sideGap
        val bottomOffset = if (isLastItem(itemPostion, parent)) firstLastGap else sideGap
        outRect.set(0, topOffset, 0, bottomOffset)
    }

    private fun isFirstItem(index: Int) = index == 0
    private fun isLastItem(index: Int, recyclerView: RecyclerView) =
        index == recyclerView.adapter!!.itemCount - 1
}