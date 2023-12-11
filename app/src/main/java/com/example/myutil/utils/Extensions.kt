package com.example.myutil.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.telephony.TelephonyManager
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.URLSpan
import android.util.Base64
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowInsetsController
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.myutil.R
import com.example.myutil.data.local.model.PostContents
import com.example.myutil.databinding.CustomUiToastmessageBinding
import com.example.myutil.ui.common.dialog.CommonDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.glide.transformations.MaskTransformation
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
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

fun Fragment.showLoginPopup() {
    CommonDialog.Builder().apply {
        title = getString(R.string.r_need_login)
        message = getString(R.string.se_r_need_login)
        positiveButtonString = getString(R.string.h_confirm)
        negativeButtonString = getString(R.string.c_cancel)
        positiveButtonClickListener = object : CommonDialog.ClickListener {
            override fun onClick() {
                context?.let {
//                    val intent = Intent(it, LoginMainActivity::class.java)
//                    intent.putExtra(
//                        ConstVariables.INTENT.EXTRA_NAV_START_DESTINATION_ID,
//                        R.id.loginWebViewFragment
//                    )
//                    startActivity(intent)
//                    activity?.finish()
                }
            }

        }
    }.build().show(parentFragmentManager, "loginDialog")
}

fun FragmentActivity.setDarkStatusBarIcon() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
    }
}

fun FragmentActivity.setWhiteStatusBarIcon() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = 0
    }
}

fun FragmentActivity.isKeyboardShown(): Boolean {
    val view = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
    val defaultKeyboardHeightDP = 100
    val estimateKeyboardDP = defaultKeyboardHeightDP + 48
    val rect = Rect()
    val estimatedKeyboardHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        estimateKeyboardDP.toFloat(),
        view.resources.displayMetrics
    ).toInt()

    view.getWindowVisibleDisplayFrame(rect)
    val heightDiff = view.rootView.height - (rect.bottom - rect.top)

    return heightDiff >= estimatedKeyboardHeight
}

fun FragmentActivity.hasSoftKeys(): Boolean {
    val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display
    } else {
        windowManager.defaultDisplay
    } ?: return true

    val realDisplayMetrics = DisplayMetrics()
    display.getRealMetrics(realDisplayMetrics)

    val realHeight = realDisplayMetrics.heightPixels
    val realWidth = realDisplayMetrics.widthPixels

    val displayMetrics = DisplayMetrics()
    display.getMetrics(displayMetrics)

    val displayHeight = displayMetrics.heightPixels
    val displayWidth = displayMetrics.widthPixels

    return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
}

fun FragmentActivity.addOnGlobalLayoutListener(globalLayoutListener: OnGlobalLayoutListener) {
    val parentView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
    parentView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
}

fun FragmentActivity.removeOnGlobalLayoutListener(globalLayoutListener: OnGlobalLayoutListener) {
    val parentView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
    parentView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
}

fun Context.getBaseImageKeyValue(uid: String?): Int {
    val profileDrawableList = listOf(R.drawable.profile_random_character1, R.drawable.profile_random_character2, R.drawable.profile_random_character3, R.drawable.profile_random_character4, R.drawable.profile_random_character5)

    if (uid.isNullOrEmpty()) return profileDrawableList[2]

    val lastChar = uid.substring(uid.length -1)
    val changeInt = lastChar.toIntOrNull()

    return if (changeInt != null) profileDrawableList[changeInt%5]
    else profileDrawableList[lastChar.toCharArray()[0].code%5]
}

fun Date.age(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(time - Date().time)
    return 1970 - (calendar.get(Calendar.YEAR) + 1)
}

fun Context.getStringByIdentifier(name: String): String {
    try {
        return getString(
            resources.getIdentifier(
                name,
                "string",
                this.packageName
            )
        )
    } catch (e: Exception) {
        Timber.e("${e.printStackTrace()}")
    }
    return ""
}

fun Context.showCustomSnackBar(snackBarView: View, message: String): Snackbar {
    val snackbar = Snackbar.make(snackBarView, "", Snackbar.LENGTH_SHORT)
    val snackBarLayout = snackbar.view as Snackbar.SnackbarLayout

    snackBarLayout.setPadding(0,0,0,resources.getDimension(R.dimen.snackbar_margin_bottom).toInt())

    val inflaterView = LayoutInflater.from(snackBarView.context).inflate(R.layout.custom_ui_toastmessage, null, false)
    snackbar.view.setBackgroundColor(Color.TRANSPARENT)

    val binding: CustomUiToastmessageBinding = CustomUiToastmessageBinding.bind(inflaterView)
    binding.root.setOnClickListener {
        if (snackbar.isShown) snackbar.dismiss()
    }
    binding.tvToastMessage.text = message
    snackBarLayout.addView(inflaterView, 0)

    snackbar.show()
    return snackbar
}

fun Context.getNickNameChange(nickName :String?,userId:String?, isFantooMember:Boolean)=
    if (nickName.isNullOrEmpty() ||
        nickName == "(이름없음)" ||
        nickName == "(No name)" ||
        nickName == "(Sin nombre)" ||
        nickName == "(Sans nom)" ||
        nickName == "(tidak ada nama)" ||
        nickName == "(名前なし)" ||
        nickName == "(Нет имени)" ||
        nickName == "(无签名)" ||
        nickName == "(沒有姓名)"
    ) {
        if(userId.isNullOrEmpty()){
            "user_****"
        }else{
            if(userId.length>4){
                if(userId.length>9){
                    userId.substring(5, 9) + "****"
                }else{
                    userId.substring(5, userId.length) + "****"
                }
            }else{
                "$userId****"
            }
        }
    } else {
        if(isFantooMember){
            nickName
        } else{
            getString(R.string.t_withdraw_member)
        }
    }

fun Context.getDeviceCountryCode(): String {
    val telephoneManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val simCode = telephoneManager.simCountryIso

    if (simCode.isEmpty()) {
        if (telephoneManager.phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
            getCDMACountryIso()?.let {
                if (it.length == 2) {
                    return it.lowercase()
                }
            }
        } else {
            val networkCode = telephoneManager.networkCountryIso
            if (networkCode.length == 2) {
                return networkCode.lowercase()
            }
        }
    } else {
        return simCode.lowercase()
    }

    val localCode = resources.configuration.locales[0].country
    if (localCode.length == 2) return localCode.lowercase()
    return "us"
}

private fun getCDMACountryIso(): String? {
    try {
        val systemProperties = Class.forName("android.os.SystemProperties")
        val get = systemProperties.getMethod("get", String::class.java)
        val homeOperator = get.invoke(systemProperties, "ro.cdma.home.operator.numeric") as String

        when (homeOperator.substring(0,3).toInt()) {
            330 -> return "PR"
            310 -> return "US"
            311 -> return "US"
            312 -> return "US"
            316 -> return "US"
            283 -> return "AM"
            460 -> return "CN"
            455 -> return "MO"
            414 -> return "MM"
            619 -> return "SL"
            450 -> return "KR"
            634 -> return "SD"
            434 -> return "UZ"
            232 -> return "AT"
            204 -> return "NL"
            262 -> return "DE"
            247 -> return "LV"
            255 -> return "UA"
        }
    } catch (e: Exception) {

    }
    return null
}

@SuppressLint("SimpleDateFormat")
fun Context.diffTimeWithCurrentTime(time: String?,
                                    timeFormat: String = "yyyy-MM-dd'T'HH:mm:ss"): String {
    val monthPattern = if(Locale.getDefault().language == LanguageUtils.KOREAN) "MM월 dd일" else "MMM dd"
    val yearsPattern = if(Locale.getDefault().language == LanguageUtils.KOREAN) "yyyy년 MM월 dd일" else "MMM dd, yyyy"
    //바꿀 포맷
    val currentYearFormat = SimpleDateFormat(monthPattern)
    currentYearFormat.timeZone= TimeZone.getDefault()
    val diffYearFormat = SimpleDateFormat(yearsPattern)
    diffYearFormat.timeZone= TimeZone.getDefault()
    //리턴값
    var newDate = "Unknown"
    try {
        val timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val dateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
        dateFormat.timeZone = timeZone
        val currentDate =dateFormat.parse(dateFormat.format(Date()))

        val oldDate = dateFormat.parse(time)
        val checkMinOrSec = (currentDate.time) - oldDate.time
        if (checkMinOrSec / 1000 < 60) {
            newDate = getString(R.string.JustNow)
        } else if (checkMinOrSec / (60 * 1000) <= 60) {
            newDate =if((checkMinOrSec / (60 * 1000))==1L){
                "${getString(R.string.MinuteAgo)}"
            }else{
                "${getString(R.string.MinutesAgo,(checkMinOrSec / (60 * 1000)))}"
            }
        } else if (checkMinOrSec / (60 * 60 * 1000) < 24) {
            newDate =if((checkMinOrSec / (60 * 60 * 1000))==1L){
                "${getString(R.string.HourAgo)}"
            }else{
                "${getString(R.string.HoursAgo,(checkMinOrSec / (60 * 60 * 1000)))}"
            }
        } else if (checkMinOrSec / (24 * 60 * 60 * 1000) < 8) {
            newDate =if((checkMinOrSec / (24 * 60 * 60 * 1000))==1L){
                "${getString(R.string.DayAgo)}"
            }else{
                "${getString(R.string.DaysAgo,(checkMinOrSec / (24 * 60 * 60 * 1000)))}"
            }
        } else {
            val calendar = GregorianCalendar()
            calendar.time=oldDate
            val postYear= calendar.get(Calendar.YEAR)
            calendar.time=currentDate
            val currentYear= calendar.get(Calendar.YEAR)
            if(postYear==currentYear){
                newDate = currentYearFormat.format(oldDate)
            }else{
                newDate = diffYearFormat.format(oldDate)
            }
        }

    } catch (e: Exception) {
    }
    return newDate
}

fun Context.navigationHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else 0
}

fun Context.statusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else 0
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

fun Long.changeTimeFormatUntilMillis(): String{
    val milliSec = this %1000
    val sec = ((this /1000)%60).toInt()
    val min = ((this /(1000*60))%60).toInt()
    val hours = ((this /(1000*60*60))%24).toInt()
    return String.format("%02d:%02d:%02d.%03d",hours,min,sec,milliSec)
}

@SuppressLint("Range")
fun Uri.asMultiPart(name: String, fileName: String?, contentResolver: ContentResolver): MultipartBody.Part? {
    return contentResolver.query(this, null, null, null, null)?.let {
        if (it.moveToNext()) {
            val displayName = fileName ?: it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            val requestBody = object : RequestBody() {
                override fun contentType(): MediaType? {
                    return contentResolver.getType(this@asMultiPart)?.toMediaType()
                }

                override fun writeTo(sink: BufferedSink) {
                    sink.writeAll(contentResolver.openInputStream(this@asMultiPart)?.source()!!)
                }
            }
            it.close()
            MultipartBody.Part.createFormData(name, displayName, requestBody)
        } else {
            it.close()
            null
        }
    }
}

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

fun BottomNavigationView.setBadge(tabResId: Int, badgeValue: Int) {
    getOnCreateBadge(this, tabResId)?.let { badge->
        badge.visibility = if (badgeValue > 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

private fun getOnCreateBadge(bottomBar: BottomNavigationView, tabResId: Int): TextView? {
    val parentView = bottomBar.findViewById<ViewGroup>(tabResId)
    Timber.d("BadgeSize iconSize => h : ${bottomBar.menu.findItem(tabResId).icon?.intrinsicHeight} | w : ${bottomBar.menu.findItem(tabResId).icon?.intrinsicWidth}")

    return parentView?.let {
        var badge = parentView.findViewById<TextView>(R.id.badge_text)
        if (badge == null) {
            LayoutInflater.from(parentView.context).inflate(R.layout.chat_badge_layout, parentView, true)
            badge = parentView.findViewById(R.id.badge_text)
        } else {
            val layoutParams = (badge.layoutParams as FrameLayout.LayoutParams)
            layoutParams.bottomMargin = (bottomBar.menu.findItem(tabResId).icon?.intrinsicHeight ?: 0) - 10
            layoutParams.marginStart = (bottomBar.menu.findItem(tabResId).icon?.intrinsicWidth ?: 0) / 2
        }
        badge
    }
}

fun ViewHolder.setHyperLinkToText(linkSeparateText: List<PostContents>): CharSequence {
    var text: CharSequence = ""

    linkSeparateText.forEachIndexed { _, postContents ->
        postContents.text.let {
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

fun ViewHolder.convertingTextContainUrl(contents: String?, ogLinkList: List<String>): ArrayList<PostContents>? {

    val linkSeparateText = arrayListOf<PostContents>()
    var cutContents = contents ?: ""

    ogLinkList.forEachIndexed { index, s ->
        if (cutContents.indexOf(s) != -1) {

            linkSeparateText.add(PostContents(ConstVariables.TEXT_CONTENTS_TYPE, cutContents.substring(0, cutContents.indexOf(s))))
            cutContents = cutContents.substring(cutContents.indexOf(s))
            linkSeparateText.add(PostContents(ConstVariables.LINK_CONTENTS_TYPE, cutContents.substring(0, s.length)))
            cutContents = cutContents.substring(s.length)

            if (index == ogLinkList.size -1){
                linkSeparateText.add(PostContents(ConstVariables.TEXT_CONTENTS_TYPE, cutContents))
            }
        }
    }

    return if (linkSeparateText.isNotEmpty()) null else linkSeparateText
}

fun RecyclerView.ViewHolder.setProfileAvatarForUser(
    imageView: ImageView,
    imageUrl: String?,
    @DrawableRes placeHolderId: Int? = null
) {
    val placeholder = placeHolderId ?: R.drawable.profile_character_xl
    val placeholderDrawable = AppCompatResources.getDrawable(imageView.context, placeholder)

    when (imageUrl) {
        null -> {
            Glide.with(imageView.context)
                .load(placeholderDrawable)
                .into(imageView)
        }
        else -> {
            Glide.with(imageView.context)
                .load(imageUrl)
                .error(placeholderDrawable)
                .apply(bitmapTransform(MultiTransformation(CenterCrop(),
                    MaskTransformation(R.drawable.bg_etc_profiles)
                )))
                .into(imageView)
        }
    }
}

fun RecyclerView.ViewHolder.setImageWithPlaceHolder(
    imageView: ImageView,
    imageUrl: String?,
    @DrawableRes maskingId: Int? = null,
    @DrawableRes placeHolderId: Int? = null
) {
    val placeholder: Drawable? = if (placeHolderId != null) {
        AppCompatResources.getDrawable(itemView.context, placeHolderId)
    } else {
        AppCompatResources.getDrawable(itemView.context, R.drawable.image_picture)
    }

    var maskingOption = RequestOptions()

    if (maskingId != null) {
        maskingOption = bitmapTransform(
            MultiTransformation(CenterCrop(), MaskTransformation(maskingId))
        )
    }

    when (imageUrl) {
        null -> {
            Glide.with(imageView.context)
                .load(placeholder)
                .apply(maskingOption)
                .error(placeholder)
                .into(imageView)
        }
        else -> {
            Glide.with(imageView.context)
                .load(imageUrl)
                .apply(maskingOption)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}