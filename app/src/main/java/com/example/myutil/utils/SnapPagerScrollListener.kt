package com.example.myutil.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import timber.log.Timber

class SnapPagerScrollListener (
    private val snapHelper: SnapHelper,
    private val notifyOnInit: Boolean,
    private val listener: OnChangeListener
) : RecyclerView.OnScrollListener() {

    private var snapSetPosition = -1
    private var snapScrollPosition = -1

    interface OnChangeListener{
        fun onSetSnapped(position: Int)
        fun onScrollSnapped(position: Int)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        notifyScrollListenerIfNeeded(getSnapPosition(recyclerView))
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        Timber.d("Scroll check State => {${newState}}] ")
        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            notifySetListenerIfNeeded(getSnapPosition(recyclerView))
        }
    }

    private fun getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManger = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION

        val snapView = snapHelper.findSnapView(layoutManger) ?: return RecyclerView.NO_POSITION

        return layoutManger.getPosition(snapView)
    }

    private fun notifySetListenerIfNeeded(newSnapPosition: Int) {
        if(snapSetPosition != newSnapPosition){
            if(notifyOnInit && !hasItemSetPosition()) {
                listener.onSetSnapped(newSnapPosition)
            }else if(hasItemSetPosition()) {
                listener.onSetSnapped(newSnapPosition)
            }
        }
        snapSetPosition=newSnapPosition
    }
    private fun notifyScrollListenerIfNeeded(newSnapPosition: Int) {
        if(snapScrollPosition != newSnapPosition){
            if(notifyOnInit && !hasItemScrollPosition()) {
                listener.onScrollSnapped(newSnapPosition)
            } else if(hasItemScrollPosition()) {
                listener.onScrollSnapped(newSnapPosition)
            }
        }
        snapScrollPosition=newSnapPosition
    }


    private fun hasItemSetPosition(): Boolean {
        return snapSetPosition != RecyclerView.NO_POSITION
    }
    private fun hasItemScrollPosition(): Boolean {
        return snapScrollPosition != RecyclerView.NO_POSITION
    }

}