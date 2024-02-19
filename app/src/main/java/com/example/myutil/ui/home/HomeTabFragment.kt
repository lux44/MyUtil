package com.example.myutil.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import com.example.myutil.BuildConfig
import com.example.myutil.R
import com.example.myutil.databinding.FragmentHomeTabBinding
import com.example.myutil.ui.common.BaseFragment
import com.example.myutil.ui.menu.DialogButton
import com.example.myutil.ui.menu.DialogClickType
import com.example.myutil.ui.menu.DialogMessage
import com.example.myutil.ui.menu.DialogTitle
import com.example.myutil.ui.menu.MenuDialogFragment
import com.example.myutil.utils.HorizontalMarginItemDecoration
import com.example.myutil.utils.ModeChanger
import com.example.myutil.utils.addSingleItemDecoration
import com.example.myutil.utils.setDarkStatusBarIcon
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeTabFragment: BaseFragment<FragmentHomeTabBinding>(FragmentHomeTabBinding::inflate) {

    // category list
    private val categoryListAdapter = CategoryListAdapter()
    private var categoryItems = listOf<Int>()

    // view pager
    lateinit var homeTabViewPagerAdapter: HomeTabPagerViewAdapter

    private val homeTabViewModel: HomeTabViewModel by viewModels()

    private lateinit var dialogFragment: MenuDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingValue()
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

    private fun settingValue() {
        categoryItems = listOf(R.string.en_newest, R.string.en_popular, R.string.en_for_you, R.string.p_fantoo_tv, R.string.p_playlist, R.string.f_fansing, R.string.h_hanryutimes)
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

    private fun initView() {
        // 상단 카테고리탭 어댑터 설정 (향후 스크롤로 변경 가능성이 있어 recyclerView로 작성)
        binding.rcCategoryList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcCategoryList.adapter = categoryListAdapter
        binding.rcCategoryList.addSingleItemDecoration(HorizontalMarginItemDecoration(20f, 5f, requireContext()))

        // 데이터 변경시 깜빡임 제거
        if (binding.rcCategoryList.itemAnimator is SimpleItemAnimator) {
            (binding.rcCategoryList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        // HomeTab 에 메인 화면 ViewPager2
        binding.rcCategoryvp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        homeTabViewPagerAdapter = HomeTabPagerViewAdapter(childFragmentManager, lifecycle)
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