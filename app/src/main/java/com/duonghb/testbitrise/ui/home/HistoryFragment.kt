package com.duonghb.testbitrise.ui.home

import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : BaseFragment<HistoryFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.history_fragment

    private val newsAdapter by lazy {
        NewsAdapter(
            clickItemCallback = {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_news_detail)
            }
        )
    }

    override fun init() {
        historyRecyclerView.adapter = newsAdapter
    }

    override fun initUi() {
    }

    override fun registerLivedataListeners() {
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
