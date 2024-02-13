package com.example.myutil.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.manager.Lifecycle

class HomeTabPagerViewAdapter(fragmentManager: FragmentManager,lifecycle: androidx.lifecycle.Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    private var fragmentList = listOf<Fragment>()
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getFragment(position: Int) = fragmentList[position]

    fun setFragmentList(items : List<Fragment>){
        this.fragmentList=items
    }

}