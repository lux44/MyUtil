package com.example.myutil.ui.home

import androidx.fragment.app.viewModels
import com.example.myutil.databinding.FragmentHomeTabBinding
import com.example.myutil.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment: BaseFragment<FragmentHomeTabBinding>(FragmentHomeTabBinding::inflate) {

    // category list
    private val categoryListAdapter = CategoryListAdapter()
    private val categoryItems = listOf<Int>()

    // view pager
    lateinit var homeTabViewPagerAdapter: HomeTabPagerViewAdapter

    private val homeTabViewModel: HomeTabViewModel by viewModels()

    override fun initUi() {
        TODO("Not yet implemented")
    }

    override fun initUiActionEvent() {
        TODO("Not yet implemented")
    }

}