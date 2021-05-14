package com.duonghb.testbitrise.ui.home

import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentNewsBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_news

    override fun init() {
        super.init()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
