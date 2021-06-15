package com.duonghb.testbitrise.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.history_fragment) {

    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HistoryFragmentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = HistoryAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                    it.url
                )
            )
        }

        binding.historyRecyclerView.adapter = adapter
        historyViewModel.getNewsHistoryListDatabase()
        historyViewModel.loadNewsHistoryListDatabaseCompleted.observe(viewLifecycleOwner) {
            adapter.setHistoryItems(it)
        }
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
