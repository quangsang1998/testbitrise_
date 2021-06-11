package com.duonghb.testbitrise.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HistoryFragmentBinding
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.history_fragment.*

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val historyFragment = R.layout.history_fragment

    private lateinit var binding: HistoryFragmentBinding

    private val historyViewModel: HistoryViewModel by viewModels()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, historyFragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initUi()
        registerLivedataListeners()
    }

    private fun init() {
        historyViewModel.getNewsHistoryListDatabase()
    }

    private fun initUi() {
        historyRecyclerView.adapter = historyAdapter
    }

    private fun registerLivedataListeners() {

        historyViewModel.loadNewsHistoryListDatabaseCompleted.observe(
            viewLifecycleOwner,
            {
                historyAdapter.setHistoryItems(it)
            }
        )
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
