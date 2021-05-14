package com.duonghb.testbitrise.ui.home

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentHomeBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun init() {
        super.init()
        safeActivity.supportActionBar?.elevation

        viewPager.adapter = viewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager) { tag, position ->
            when (position) {
                0 -> tag.text = "News"
                1 -> tag.text = "History"
            }
        }.attach()

//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            when (position) {
//                0 -> {
//                    tab.text = "News"
//                }
//
//                1 -> {
//                    tab.text = "History"
//                }
//                else -> {
//                }
//            }
//        }.attach()


//        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback {
//            override fun onPageSelected(position: Int) {
//                viewPager.isSelected
//            }
//        })
    }

    override fun initUi() {
        super.initUi()
        // newsRecyclerView.adapter = newsAdapter
    }

    override fun registerLivadataListeners() {
        super.registerLivadataListeners()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
