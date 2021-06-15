package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsFragment = R.layout.news_fragment

    private lateinit var binding: NewsFragmentBinding

    private val adapter = GroupAdapter<GroupieViewHolder>()

    fun generateItemNewGroupie(newsModel: NewsModel): MutableList<ItemNewGroupie> {
        return MutableList(newsModel.results.size) {
            ItemNewGroupie(
                clickItemCallback = {
                    findNavController().navigate(
                        HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                            it.url
                        )
                    )

                    newsViewModel.saveNewsModelDatabase(it.copy(time = System.currentTimeMillis()))
                }
            )
        }
    }

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                newsViewModel.swipeRefreshingData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, newsFragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newsViewModel = newsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        registerLiveDataListener()
    }

    private fun initUi() {
        setHasOptionsMenu(true)

        newsRecyclerView.adapter = adapter
        newsRecyclerView.setItemViewCacheSize(20)
    }

    private fun registerLiveDataListener() {
        newsViewModel.loadNewsCompleted.observe(
            viewLifecycleOwner,
            Observer {
                adapter.updateAsync(generateItemNewGroupie(it))
            }
        )
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
