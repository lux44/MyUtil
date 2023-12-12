package com.example.myutil.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import timber.log.Timber

class TextUtils {

    companion object {
        fun applyParticalColor(
            frontText: String,
            endText: String,
            color: Int,
            tv: TextView,
            isApplyFrontText: Boolean
        ) {
            try {
                val spannable = getParticalColorSpannable(frontText, endText, color, isApplyFrontText)
                tv.setText(spannable, TextView.BufferType.SPANNABLE)
            } catch (e: Exception) {
                Timber.e("${e.printStackTrace()}")
            }
        }

        fun getParticalColorSpannable(
            frontText: String,
            endText: String,
            color: Int,
            isApplyFrontText: Boolean
        ): Spannable {
            val spannable = SpannableString(frontText + endText)
            try {
                val startApplyPos = if (isApplyFrontText) 0 else frontText.length
                val endApplyPos = if (isApplyFrontText) frontText.length else (frontText + endText).length

                spannable.setSpan(
                    ForegroundColorSpan(color),
                    startApplyPos,
                    endApplyPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } catch (e: Exception) {
                Timber.e("${e.printStackTrace()}")
            }

            return spannable
        }

        //클럽프로필 파일명 중복방지를 위한 랜덤String생성
        fun getRandomString(length: Int) : String {
            val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return (1..length)
                .map { charset.random() }
                .joinToString("")
        }
    }
}