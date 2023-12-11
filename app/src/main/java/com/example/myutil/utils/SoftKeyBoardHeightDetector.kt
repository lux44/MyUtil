package com.example.myutil.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager.LayoutParams
import android.widget.PopupWindow
import androidx.compose.ui.geometry.Rect
import com.example.myutil.R

class SoftKeyBoardHeightDetector(private val activity: Activity): PopupWindow(activity) {

    private var resizableView: View
    private var parentView: View? = null
    private var heightMax: Int = -1

    private var keyboardListeners = ArrayList<KeyboardListener>()

    init {
        contentView = View.inflate(activity, R.layout.keyboard_popup, null)
        resizableView = contentView.findViewById(R.id.keyResizeContainer)

        softInputMode = LayoutParams.SOFT_INPUT_ADJUST_RESIZE or LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        inputMethodMode = INPUT_METHOD_NEEDED

        width = 0
        height = LayoutParams.MATCH_PARENT
    }

    fun onResume() {
        parentView = activity.findViewById(android.R.id.content)
        parentView?.post {
            resizableView.viewTreeObserver.addOnGlobalLayoutListener(getGlobalLayoutListener())
            if (!isShowing && parentView?.windowToken != null) {
                showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0)
            }
        }
    }

    fun onPause() {
        resizableView.viewTreeObserver.removeOnGlobalLayoutListener(getGlobalLayoutListener())
        dismiss()
    }

    private fun getGlobalLayoutListener() = ViewTreeObserver.OnGlobalLayoutListener {
        computeKeyboardState()
    }

    private fun computeKeyboardState() {
        val rect = android.graphics.Rect()

        resizableView.getWindowVisibleDisplayFrame(rect)

        val keyboardHeight = SizeUtils.getDeviceSize(activity).height - activity.navigationHeight() - rect.bottom
        KeyboardInfo.keyboardState = if (keyboardHeight > 0) KeyboardInfo.STATE_OPENED else KeyboardInfo.STATE_CLOSED

        if (keyboardHeight > 0) {
            KeyboardInfo.keyboardHeight = keyboardHeight
        }

        if (keyboardHeight != heightMax) {
            notifyKeyboardHeightChanged(keyboardHeight)
        }
        heightMax = keyboardHeight
    }

    fun addKeyboardListener(listener: KeyboardListener) {
        keyboardListeners.add(listener)
    }

    fun removeKeyboardListener(listener: KeyboardListener) {
        keyboardListeners.remove(listener)
    }

    private fun notifyKeyboardHeightChanged(height: Int) {
        keyboardListeners.forEach {
            it.onHeightChanged(height)
        }
    }

    interface KeyboardListener {
        fun onHeightChanged(height: Int)
    }
}