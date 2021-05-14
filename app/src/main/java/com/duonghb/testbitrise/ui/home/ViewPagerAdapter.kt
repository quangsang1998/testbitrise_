package com.duonghb.testbitrise.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duonghb.testbitrise.R

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0  -> {
                NewsFragment()
            }
            1  -> {
                HistoryFragment()
            }
            else -> {
                NewsFragment()
            }
        }
    }

//
//    override fun getItemViewType(position: Int): Int {
//        return fragmentList[position]
//    }
}
