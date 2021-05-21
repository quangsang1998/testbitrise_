package com.duonghb.testbitrise.ui.home

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentHomeBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val newsAdapter by lazy {
        NewsAdapter(
            clickItemCallback = {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_news_detail)
            }
        )
    }

    override fun init() {
    }

    override fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(false)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> safeActivity.supportActionBar?.setTitle(R.string.title_news)
                    1 -> safeActivity.supportActionBar?.setTitle(R.string.title_history)
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        TabLayoutMediator(tabLayout, viewPager) { tag, position ->
            when (position) {
                0 -> {
                    tag.setText(R.string.title_news)
                }
                1 -> {
                    tag.setText(R.string.title_history)
                }
            }
        }.attach()
    }

    override fun registerLivedataListeners() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
