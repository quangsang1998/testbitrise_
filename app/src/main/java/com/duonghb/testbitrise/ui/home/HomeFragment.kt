package com.duonghb.testbitrise.ui.home

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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

    override fun init() {
    }

    override fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(false)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val actionBar = safeActivity.supportActionBar

        val textActionBar = SpannableString(actionBar?.title)
        textActionBar.setSpan(ForegroundColorSpan(Color.BLACK), 0, textActionBar.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> actionBar?.setTitle(R.string.title_news)
                    1 -> actionBar?.setTitle(R.string.title_history)
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

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("red"))
        tabLayout.setTabTextColors(Color.parseColor("black"), Color.parseColor("black"))
    }

    override fun registerLivedataListeners() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
