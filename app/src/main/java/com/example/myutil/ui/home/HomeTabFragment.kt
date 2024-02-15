package com.example.myutil.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.myutil.R
import com.example.myutil.databinding.FragmentHomeTabBinding
import com.example.myutil.ui.common.BaseFragment
import com.example.myutil.utils.setDarkStatusBarIcon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment: BaseFragment<FragmentHomeTabBinding>(FragmentHomeTabBinding::inflate) {

    // category list
    private val categoryListAdapter = CategoryListAdapter()
    private val categoryItems = listOf<Int>()

    // view pager
    lateinit var homeTabViewPagerAdapter: HomeTabPagerViewAdapter

    private val homeTabViewModel: HomeTabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initUi() {
        // 상태바 색상 변경
        activity?.let {
            activity?.window?.statusBarColor = it.getColor(R.color.bg_bg_light_gray_50)
            it.setDarkStatusBarIcon()
        }
    }

    override fun initUiActionEvent() {
        TODO("Not yet implemented")
    }

    private fun initToolbar() {

    }

}