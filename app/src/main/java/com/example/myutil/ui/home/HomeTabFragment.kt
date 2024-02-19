package com.example.myutil.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myutil.BuildConfig
import com.example.myutil.R
import com.example.myutil.databinding.FragmentHomeTabBinding
import com.example.myutil.ui.common.BaseFragment
import com.example.myutil.ui.menu.DialogButton
import com.example.myutil.ui.menu.DialogClickType
import com.example.myutil.ui.menu.DialogMessage
import com.example.myutil.ui.menu.DialogTitle
import com.example.myutil.ui.menu.MenuDialogFragment
import com.example.myutil.utils.ModeChanger
import com.example.myutil.utils.setDarkStatusBarIcon
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeTabFragment: BaseFragment<FragmentHomeTabBinding>(FragmentHomeTabBinding::inflate) {

    // category list
    private val categoryListAdapter = CategoryListAdapter()
    private val categoryItems = listOf<Int>()

    // view pager
    lateinit var homeTabViewPagerAdapter: HomeTabPagerViewAdapter

    private val homeTabViewModel: HomeTabViewModel by viewModels()

    private lateinit var dialogFragment: MenuDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initUi() {
        // 상태바 색상 변경
        activity?.let {
            activity?.window?.statusBarColor = it.getColor(R.color.bg_bg_light_gray_50)
            it.setDarkStatusBarIcon()
        }
        initToolbar()
    }

    override fun initUiActionEvent() {
        TODO("Not yet implemented")
    }

    // Tool bar 정의
    private fun initToolbar() {
        if (homeTabViewModel.appMode == ModeChanger.MODE_DEBUG && BuildConfig.DEBUG) {
            binding.tvDevText.visibility = View.VISIBLE
        } else {
            binding.tvDevText.visibility = View.GONE
        }

        // 추가 작업 필요
        binding.homeToolbar.setOnMenuItemClickListener { menuItem->
            when (menuItem.itemId) {
                R.id.menu_search -> {
                    true
                }
                R.id.menu_shop_and_event -> {
                    true
                }
                R.id.menu_menu -> {
                    true
                }
                else -> false

            }
        }
    }

    private fun showLoginMessage() {
        val message = DialogMessage(
            DialogTitle(
                getString(R.string.r_need_login),
                getString(R.string.se_r_need_login),
                null
            ),
            DialogButton(getString(R.string.h_confirm), getString(R.string.c_cancel), true),
            false
        )

        dialogFragment = MenuDialogFragment(message) { clickType ->
            when (clickType) {
                DialogClickType.OK -> {
                    Timber.d("ok")
                }
                DialogClickType.CANCEL -> {
                    Timber.d("cancel")
                }
            }
            dismissDialog()
        }
        openDialog()
    }

    private fun dismissDialog() {
        Timber.d("dismiss dialog")
        dialogFragment.dismiss()
    }

    private fun openDialog() {
        Timber.d("open dialog")
        dialogFragment.show(
            requireActivity().supportFragmentManager,
            MenuDialogFragment.DIALOG_MENU
        )
    }

}