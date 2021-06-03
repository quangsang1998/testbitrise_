package com.duonghb.testbitrise.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duonghb.testbitrise.ui.history.HistoryFragment
import com.duonghb.testbitrise.ui.news.NewsFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewsFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> {
                NewsFragment()
            }
        }
    }
}
