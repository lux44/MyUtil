package com.example.myutil.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myutil.R
import com.example.myutil.databinding.DialogMenuBinding
import com.example.myutil.utils.setTextAndVisibility

class MenuDialogFragment(
    private val message: DialogMessage,
    private val onItemClicked: (DialogClickType) -> Unit
): AppCompatDialogFragment() {
    lateinit var binding: DialogMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setMessage(message: DialogMessage) {
        message.title.title?.let {
            binding.msgTitle.setTextAndVisibility(it, View.VISIBLE)
        }
        message.title.subTitle?.let {
            binding.msgSubTitle.setTextAndVisibility(it, View.VISIBLE)
        }
        message.title.extraMessage?.let {
            binding.msgExtraTitle.setTextAndVisibility(it, View.VISIBLE)

            val params = binding.okBtn.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = resources.getDimensionPixelSize(R.dimen.spacing_twelve)
            binding.okBtn.layoutParams = params
        }

        if (message.button.isTwoButton) {
            binding.okBtn.visibility = View.GONE
            binding.okTwoBtn.setTextAndVisibility(message.button.okTitle, View.VISIBLE)
            binding.cancelTwoBtn.visibility = View.VISIBLE

            message.button.cancelTitle?.let {
                binding.cancelTwoBtn.text = it
            }

            binding.okTwoBtn.setOnClickListener {
                onItemClicked(DialogClickType.OK)
            }
            binding.cancelTwoBtn.setOnClickListener {
                onItemClicked(DialogClickType.CANCEL)
            }
        } else {
            binding.okBtn.setTextAndVisibility(message.button.okTitle, View.VISIBLE)
            binding.okTwoBtn.visibility = View.GONE
            binding.cancelTwoBtn.visibility = View.GONE

            binding.okBtn.setOnClickListener {
                onItemClicked(DialogClickType.OK)
            }
        }

        if (message.isCompleted) {
            binding.fanitContainer.visibility = View.VISIBLE
        } else {
            binding.fanitContainer.visibility = View.GONE
        }

    }

    companion object {
        const val DIALOG_MENU = "dialog_menu"
    }



}