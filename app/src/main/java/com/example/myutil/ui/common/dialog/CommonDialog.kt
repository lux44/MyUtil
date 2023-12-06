package com.example.myutil.ui.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.myutil.databinding.CommonDialogBinding
import com.example.myutil.utils.SizeUtils
import java.lang.IllegalStateException

open class CommonDialog(): DialogFragment() {

    protected var builder: Builder? = null
    private var _binding: CommonDialogBinding? = null
    private val binding get() = _binding!!
    private constructor(builder: Builder): this() {
        this.builder = builder
    }


    protected open fun initView() {
        binding.apply {

            rlRoot.setOnClickListener {
                builder?.cancelListener?.onCancel()
                if (builder?.isCanceledOnTouchOutside == true) dismiss()
            }

            if (!builder?.title.isNullOrEmpty()) {
                tvTitle.text = builder?.title
                tvTitle.visibility = View.VISIBLE
            }

            if (!builder?.spannableTitle.isNullOrEmpty()) {
                tvTitle.text = builder?.spannableTitle
                tvTitle.visibility = View.VISIBLE
            }

            if (!builder?.titleOption.isNullOrEmpty()) {
                tvTitleOption.apply {
                    text = builder?.titleOption
                    visibility = View.VISIBLE
                    setOnClickListener { onTitleOptionClick() }
                }
            }

            if (builder?.messageGravity != -1) {
                tvMessage.gravity = builder?.messageGravity ?: Gravity.CENTER
            }

            if (!builder?.messageSpannable.isNullOrEmpty()) {
                tvMessage.apply {
                    setText(builder!!.messageSpannable, TextView.BufferType.SPANNABLE)
                    visibility = View.VISIBLE
                }
            }

            if (!builder?.tailMessage.isNullOrEmpty()) {
                tvTailMessage.apply {
                    text = builder!!.tailMessage
                    visibility = View.VISIBLE
                }
            }

            if (builder?.hasInputField == true) {
                rlInput.visibility = View.VISIBLE
                etInput.addTextChangedListener(textWatcher)

                if (builder!!.inputHint.isNotEmpty()) {
                    etInput.hint = builder!!.inputHint
                }
                flInputCancel.setOnClickListener {
                    etInput.setText("")
                }
            }

            if (!builder?.negativeButtonString.isNullOrEmpty()) {
                llOneButton.visibility = View.GONE

                val isCustomize = (builder!!.positiveButtonWidth != -1 || builder?.negativeButtonWidth != -1)
                if (isCustomize) {
                    llTwoButton.visibility = View.GONE
                    llCustomSizeTwoButton.visibility = View.VISIBLE

                    if (builder!!.positiveButtonWidth != -1) {
                        context?.let {
                            btnCSTwoButtonPositive.apply {
                                width = builder!!.positiveButtonWidth
                                text = builder!!.positiveButtonString
                                setOnClickListener { onPositiveButtonClick() }
                            }
                        }
                    }

                    if (builder!!.negativeButtonWidth != -1) {
                        context?.let {
                            btnCSTwoButtonNegative.apply {
                                width = builder!!.negativeButtonWidth
                                text = builder!!.negativeButtonString
                                setOnClickListener { onNegativeButtonClick() }
                            }
                        }
                    }
                } else {
                    llTwoButton.visibility = View.VISIBLE
                    btnTwoButtonPositive.text = builder!!.positiveButtonString
                    btnTwoButtonPositive.setOnClickListener { onPositiveButtonClick() }

                    btnTwoButtonNegative.text = builder!!.negativeButtonString
                    btnTwoButtonNegative.setOnClickListener { onNegativeButtonClick() }
                }
            } else {
                btnOneButtonPositive.text = builder?.positiveButtonString
                btnOneButtonPositive.setOnClickListener { onPositiveButtonClick() }
            }

            if (builder?.hasInputField == true) etInput.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            requestFeature(Window.FEATURE_NO_TITLE)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = android.graphics.Color.TRANSPARENT
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            try {
                val width = WindowManager.LayoutParams.MATCH_PARENT
                val height = WindowManager.LayoutParams.MATCH_PARENT
                dialog.window!!.setLayout(width, height)

                val rootLayoutParams = binding.rlRoot.layoutParams
                var titleBarHeight = 0
                val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

                if (resourceId > 0) {
                    titleBarHeight = resources.getDimensionPixelSize(resourceId)
                }

                rootLayoutParams.height = context?.let { SizeUtils.getDeviceSize(it).height - titleBarHeight }!!
                binding.rlRoot.layoutParams = rootLayoutParams
            } catch (e:Exception) {

            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            _binding = CommonDialogBinding.inflate(layoutInflater, null, false)
            initView()
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            return builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }

    private var textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.flInputCancel.visibility = if (p0?.length!! > 0) View.VISIBLE else View.GONE
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }
    private fun onTitleOptionClick() {
        builder?.titleOptionClickListener?.onClick()
        dialog?.dismiss()
    }
    open fun onPositiveButtonClick() {
        if (builder?.hasInputField == true) {
            builder!!.inputFieldCallback?.onInputText(binding.etInput.text.toString())
        }
        builder?.positiveButtonClickListener?.onClick()
        dialog?.dismiss()
    }

    open fun onNegativeButtonClick() {
        builder?.negativeButtonClickListener?.onClick()
        dialog?.dismiss()
    }

    open class Builder{
        var isCanceledOnTouchOutside = true
        var positiveButtonClickListener: ClickListener? = null
        var negativeButtonClickListener: ClickListener? = null
        var titleOptionClickListener : ClickListener? = null
        var cancelListener : CancelListener? = null
        var inputFieldCallback: InputFieldCallBack? = null
        var title:String? = null
        var spannableTitle : SpannableString? =null
        var titleOption:String? = null
        var message:String? = null
        var tailMessage:String? = null
        var inputHint:String = ""
        var hasInputField:Boolean = false
        var messageSpannable: Spannable? = null
        var hasTwoButton:Boolean = false

        var positiveButtonString:String = ""
        var negativeButtonString: String = ""

        /**
        @Important! assign pixel size
         */
        var positiveButtonWidth:Int = -1
        /**
        @Important! assign pixel size
         */
        var negativeButtonWidth:Int = -1

        var messageGravity:Int = -1

        fun title(title:String) = apply { this.title = title }
        fun spannableTitle(title:String) = apply { this.title = title }
        fun titleOption(titleOption:String) = apply { this.titleOption = titleOption }
        fun message(message: String) = apply { this.message = message }
        fun spannableMessage(message: String) = apply { this.message = message }
        fun tailMessage(message: String) = apply { this.tailMessage = message }
        fun setPositiveButtonText(text:String) = apply { this.positiveButtonString = text }
        fun setNegativeButtonText(text:String) = apply { this.negativeButtonString = text }
        fun setEnableInputField(enable:Boolean) = apply { this.hasInputField = enable}
        fun setInputFieldCallBack(inputFieldCallBack: InputFieldCallBack) = apply { this.inputFieldCallback = inputFieldCallBack }
        fun setInputFieldHintText(text:String) = apply {this.inputHint = text }
        fun setPositiveButtonClickListener(listener: ClickListener) = apply {this.positiveButtonClickListener = listener}
        fun setNegativeButtonClickListener(listener: ClickListener) = apply { this.negativeButtonClickListener = listener}
        fun setTitleOptionClickListener(listener: ClickListener) = apply { this.titleOptionClickListener = listener }
        fun setMessageSpannable(messageSpannable: Spannable) = apply { this.messageSpannable = messageSpannable }
        fun setCanceledOnTouchOutside(isCanceledOnTouchOutside: Boolean) = apply { this.isCanceledOnTouchOutside =  isCanceledOnTouchOutside}
        fun setOnCancelListener(cancelListener: CancelListener) = apply { this.cancelListener = cancelListener }
        open fun build() = CommonDialog(this)
    }

    interface InputFieldCallBack{
        fun onInputText(string:String)
    }

    interface ClickListener{
        fun onClick()
    }

    interface CancelListener{
        fun onCancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

