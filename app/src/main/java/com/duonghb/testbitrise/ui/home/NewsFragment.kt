package com.duonghb.testbitrise.ui.home

import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : BaseFragment<NewsFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.news_fragment

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
        newsRecyclerView.adapter = newsAdapter
    }

    override fun registerLivedataListeners() {
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
