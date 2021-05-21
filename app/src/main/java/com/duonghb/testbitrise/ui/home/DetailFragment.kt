package com.duonghb.testbitrise.ui.home

import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.DetailFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.detail_fragment

    override fun onResume() {
        super.onResume()
        safeActivity.supportActionBar?.setTitle(R.string.title_news_detail)
    }

    override fun init() {
    }

    override fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerLivedataListeners() {
    }
}
