package com.duonghb.testbitrise.ui.home

import androidx.viewpager2.widget.ViewPager2
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentHomeBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun init() {
        super.init()
        safeActivity.supportActionBar?.elevation

        viewPager.adapter = viewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tag, position ->
            when (position) {
                0 -> {
                    tag.text = "News"
                }
                1 -> {
                    tag.text = "History"
                }
            }
        }.attach()
    }

    override fun initUi() {
        super.initUi()
    }

    override fun registerLivedataListeners() {
        super.registerLivedataListeners()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
