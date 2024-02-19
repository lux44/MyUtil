package com.example.myutil.ui.common.viewgroup

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myutil.R
import com.example.myutil.databinding.CommonTranslateButtonBinding

class TranslateButton : ConstraintLayout{

    private lateinit var binding: CommonTranslateButtonBinding

    constructor(context: Context):this(context, null)

    constructor(context: Context, attribute: AttributeSet?):this(context, attribute, 0)

    constructor(context: Context, attribute: AttributeSet?, style:Int):super(context, attribute, style){
        init()
    }

    fun init(){
        binding = CommonTranslateButtonBinding.bind(LayoutInflater.from(context).inflate(R.layout.common_translate_button, this))
        setTranslateState(TranslateState.TRANSLATE_STATE_NONE)
    }

    fun setTranslateState(state:Int){
        when(state){
            TranslateState.TRANSLATE_STATE_NONE ->{
                binding.ivTranslate.visibility = View.VISIBLE
                binding.translateLoading.visibility = View.GONE
                binding.ivTranslate.setImageResource(R.drawable.icon_translation_n)
                binding.ivTranslate.colorFilter = PorterDuffColorFilter(
                    context.getColor(R.color.dark_blue_500),
                    PorterDuff.Mode.SRC_IN)
            }
            TranslateState.TRANSLATE_STATE_DOING ->{
                binding.translateLoading.visibility = View.VISIBLE
                binding.ivTranslate.visibility = View.GONE
            }
            TranslateState.TRANSLATE_STATE_DONE ->{
                binding.ivTranslate.visibility = View.VISIBLE
                binding.translateLoading.visibility = View.GONE
                binding.ivTranslate.setImageResource(R.drawable.icon_translation_t)
                binding.ivTranslate.clearColorFilter()
            }
        }
    }


}

object TranslateState{
    const val TRANSLATE_STATE_NONE = 0
    const val TRANSLATE_STATE_DOING = 1
    const val TRANSLATE_STATE_DONE = 2
}