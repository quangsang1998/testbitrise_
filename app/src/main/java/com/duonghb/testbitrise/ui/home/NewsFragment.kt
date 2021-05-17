package com.duonghb.testbitrise.ui.home

import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentNewsBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_news

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun init() {
        super.init()
        newsRecyclerView.adapter = newsAdapter
    }

    override fun registerLivedataListeners() {
        super.registerLivedataListeners()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
