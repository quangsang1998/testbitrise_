package com.duonghb.testbitrise.ui.home

import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentHistoryBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_history

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun init() {
        super.init()
        historyRecyclerView.adapter = newsAdapter
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
