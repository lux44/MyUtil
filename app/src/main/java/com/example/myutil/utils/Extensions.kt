package com.example.myutil.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.URLSpan
import android.util.Base64
import android.webkit.WebView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myutil.R
import com.example.myutil.data.local.model.PostContents
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.StringBuilder
import java.net.URI
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Calendar
import java.util.Date
import javax.crypto.Cipher



fun Activity.applyDefaultFontScale(configuration: Configuration) {
    if (configuration != null && configuration.fontScale != 1.0f) {
        configuration.fontScale = 1.0.toFloat()
        val metrics = resources.displayMetrics
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            Resources.getSystem().displayMetrics.apply { metrics }
        } else {
            val display = windowManager.defaultDisplay
            display.getMetrics(metrics)
        }

        metrics.scaledDensity = configuration.fontScale * metrics.density
        resources.updateConfiguration(configuration, metrics)
    }
}

fun Activity.shareLink(shareLink: String) {
    startActivity(
        Intent.createChooser(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareLink)
                type = "text/plain"
            },
            "null"
        )
    )
}

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
fun Date.age(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(time - Date().time)
    return 1970 - (calendar.get(Calendar.YEAR) + 1)
}

fun String.encryptString(context: Context): String {
    var encryptedStringResult = ""

    try {
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        val inputStream: InputStream = context.resources.openRawResource(R.raw.rsa_public_key)
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int

        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            Timber.e("exception : $e")
        }

        val keyString: String = outputStream.toString().replace("\\r".toRegex(), "")
            .replace("\\n".toRegex(), "")
            .replace(System.lineSeparator().toRegex(), "")
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")

        val spec = X509EncodedKeySpec(Base64.decode(keyString, Base64.DEFAULT))//Base64.getDecoder().decode(keyString))///
        val publicKey: Key = keyFactory.generatePublic(spec)
        val encCipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        encCipher.init(Cipher.ENCRYPT_MODE, publicKey)

        val encryptTextByteArray = encCipher.doFinal(this.toByteArray(StandardCharsets.UTF_8))
        encryptedStringResult = Base64.encodeToString(encryptTextByteArray, Base64.NO_WRAP or Base64.URL_SAFE)
    } catch (e: Exception) {
        Timber.e("${e.printStackTrace()}")
    }

    return encryptedStringResult
}

fun String.fromBase64(): String = String(Base64.decode(this, Base64.DEFAULT), StandardCharsets.UTF_8)

fun String.toBase64(): String = String(Base64.encode(this.toByteArray(), Base64.DEFAULT), StandardCharsets.UTF_8)

fun Float.toDp(): Float = this * Resources.getSystem().displayMetrics.density + 0.5f

fun WebView.executeScript(
    functionName: String,
    params: List<Any> = emptyList(),
    onResult: (value: String) -> Unit = {}
) {
    val sb = StringBuilder()

    sb.append("javascript:")
        .append(functionName)
        .append("('")
        .append(params.joinToString(", "))
        .append("')")

    Timber.d("executeScript: $sb")
    evaluateJavascript(sb.toString(), onResult)
}

fun TextView.setLeftMarginSpan(leftStandardText: String, fullText: String) {
    try {
        val startMargin = this.paint.measureText(leftStandardText).toInt()

        val hasNewLineChar = (fullText.indexOf("\n") != -1)
        val spannableSb = SpannableStringBuilder()
        var len: Int

        if (hasNewLineChar) {   // 개행문자가 포함된 경우 leftMargin 적용
            val splitString = fullText.split("\n")
            var firstParagraph = 0

            for (k in splitString.indices) {
                len = spannableSb.length
                if (k > 0) firstParagraph = startMargin

                spannableSb.apply {
                    append(splitString[k])
                    append("\n")
                    setSpan(
                        LeadingMarginSpan.Standard(firstParagraph, startMargin),
                        len,
                        len + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }

            this.text = spannableSb
        } else {
            this.text = SpannableString(text).apply {
                setSpan(
                    LeadingMarginSpan.Standard(0, startMargin),
                    0,
                    length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    } catch (e: Exception) {
        Timber.e("${e.printStackTrace()}")
    }
}

fun ViewHolder.setHyperLinkToText(linkSeparateText: List<PostContents>): CharSequence {
    var text: CharSequence = ""

    linkSeparateText.forEachIndexed { _, postContents ->
        postContents.text?.let {
            val spannableString = SpannableString(it)
            if (postContents.type == ConstVariables.LINK_CONTENTS_TYPE) {
                spannableString.apply {
                    setSpan(
                        URLSpan(postContents.text),
                        0,
                        it.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(Color.parseColor("#4787E7")),
                        0,
                        it.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            text = TextUtils.concat(text, spannableString)
        }
    }
    return text
}