package com.example.myutil.ui.common.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.myutil.R
import com.example.myutil.databinding.LayoutLoadingBinding
import org.w3c.dom.Attr


class LoadingView: ConstraintLayout {

    lateinit var binding: LayoutLoadingBinding

    constructor(context: Context): super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ): super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView() {
        val infService =  Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(infService) as LayoutInflater

        binding = LayoutLoadingBinding.inflate(inflater)
        addView(binding.root)

        setImageView()
    }

    private fun setImageView() {
        val layoutManager = binding.ivLoadingImage.layoutParams as LayoutParams

        layoutManager.topToTop = LayoutParams.PARENT_ID
        layoutManager.bottomToBottom = LayoutParams.PARENT_ID
        layoutManager.startToStart = LayoutParams.PARENT_ID
        layoutManager.endToEnd = LayoutParams.PARENT_ID

        binding.ivLoadingImage.layoutParams = layoutManager
        binding.ivBackground.alpha = 0.7f

        Glide.with(context)
            .load(R.drawable.character_loading)
            .into(binding.ivLoadingImage)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
//        return super.generateDefaultLayoutParams()
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }
}