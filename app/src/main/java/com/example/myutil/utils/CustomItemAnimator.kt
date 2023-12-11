package com.example.myutil.utils

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class CustomItemAnimator : DefaultItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {
        // 데이터 변경 애니메이션을 제거
        dispatchChangeFinished(oldHolder, true)
        dispatchChangeFinished(newHolder, false)
        return false
    }
}