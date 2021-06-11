package com.duonghb.testbitrise.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.duonghb.testbitrise.R
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeFragment = R.layout.fragment_home

    private lateinit var binding: ViewDataBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val safeActivity by lazy {
        requireActivity() as AppCompatActivity
    }

    enum class PositionValue(val value: Int) {
        FIRST_TAG(0),
        SECOND_TAG(1),
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, homeFragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(false)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val actionBar = safeActivity.supportActionBar

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    PositionValue.FIRST_TAG.value -> actionBar?.setTitle(R.string.title_news)
                    PositionValue.SECOND_TAG.value -> actionBar?.setTitle(R.string.title_history)
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
                PositionValue.FIRST_TAG.value -> {
                    tag.setText(R.string.title_news)
                }
                PositionValue.SECOND_TAG.value -> {
                    tag.setText(R.string.title_history)
                }
            }
        }.attach()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
