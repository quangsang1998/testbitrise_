package com.duonghb.testbitrise.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.history.HistoryViewModel.EventHistory
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {

    private val historyViewModel: HistoryViewModel by viewModels()

    @Inject
    lateinit var adapterHistory: GroupAdapter<GroupieViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HistoryFragmentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.historyRecyclerView.adapter = adapterHistory

        historyViewModel.getNewsHistoryListDatabase()

        historyViewModel.loadHistories.observe(viewLifecycleOwner, ::setItems)

        historyViewModel.onEventHistory.observe(viewLifecycleOwner, ::handleEventHistory)
    }

    private fun setItems(news: List<HistoryListItemViewModel>) {
        adapterHistory.updateAsync(news.map { HistoryListItem(it) })
    }

    private fun handleEventHistory(event: EventHistory) {
        when (event) {
            EventHistory.ClickedClose -> {
            }
            is EventHistory.ClickedItem -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                        event.historyItem.url
                    )
                )
            }
        }
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
