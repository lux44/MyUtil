package com.example.myutil.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Size
import android.view.WindowManager

class SizeUtils {
    companion object {
        fun getDeviceSize(context: Context): Size {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                val metrics = context.getSystemService(WindowManager::class.java).currentWindowMetrics
                Size(metrics.bounds.width(), metrics.bounds.height())
            } else {
                val display = context.getSystemService(WindowManager::class.java).defaultDisplay
                val metrics = if (display != null) {
                    DisplayMetrics().also { display.getRealMetrics(it) }
                } else {
                    Resources.getSystem().displayMetrics
                }
                Size(metrics.widthPixels, metrics.heightPixels)
            }
        }
    }
}