package com.duonghb.testbitrise.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.history_fragment.*

@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.history_fragment

    private val historyAdapter by lazy {
        HistoryAdapter(
            clickHistoryItemCallback = {
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(it.url))
            }
        )
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun init() {
        viewModel.getNewsHistoryListDatabase()
    }

    override fun initUi() {
        historyRecyclerView.adapter = historyAdapter
    }

    override fun registerLivedataListeners() {
        viewModel.saveNewsHistoryDatabaseCompleted.observe(
            this,
            {
                viewModel.getNewsHistoryListDatabase()
            }
        )

        viewModel.loadNewsHistoryListDatabaseCompleted.observe(
            this,
            {
                historyAdapter.setHistoryItems(it)
            }
        )
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
