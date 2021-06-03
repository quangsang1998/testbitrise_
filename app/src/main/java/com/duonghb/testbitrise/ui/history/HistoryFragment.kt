package com.duonghb.testbitrise.ui.history

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.history_fragment.*

@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.history_fragment

    private val viewModelHistory: HistoryViewModel by viewModels()

    private val historyAdapter by lazy {
        HistoryAdapter(
            clickHistoryItemCallback = {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                        it.url
                    )
                )
            }
        )
    }

    override fun init() {
        viewModelHistory.getNewsHistoryListDatabase()
    }

    override fun initUi() {
        historyRecyclerView.adapter = historyAdapter
    }

    override fun registerLivedataListeners() {

        viewModelHistory.loadNewsHistoryListDatabaseCompleted.observe(
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
