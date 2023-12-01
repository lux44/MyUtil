package com.example.myutil.utils

import android.app.Activity
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import java.net.URI

fun Fragment.moveToDeepLinkRequest(uri: Uri) {
    try {
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri)
            .build()
        findNavController().navigate(request)
    } catch (e:Exception) {
        Timber.e("${e.printStackTrace()}")
    }
}

fun Activity.applyDefaultFontScale(configuration: Configuration) {
    if (configuration != null && configuration.fontScale != 1.0f) {
        configuration.fontScale = 1.0.toFloat()
        val metrics = resources.displayMetrics
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {

        }
    }
}