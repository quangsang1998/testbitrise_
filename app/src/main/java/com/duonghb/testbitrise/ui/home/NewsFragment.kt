package com.duonghb.testbitrise.ui.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.news_fragment

    private val newsAdapter by lazy {
        NewsAdapter(
            clickItemCallback = {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                        it.url
                    )
                )
                viewModel.saveNewsModelDatabase(it)
            }
        )
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                newsSwipeRefresh.isRefreshing = true
                viewModel.loadData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun init() {
        setHasOptionsMenu(true)
        viewModel.loadData()
    }

    override fun initUi() {
        newsRecyclerView.adapter = newsAdapter
    }

    override fun registerLivedataListeners() {
        viewModel.loadNewsCompleted.observe(
            this,
            Observer {
                newsAdapter.setItems(it)
                newsSwipeRefresh.isRefreshing = false
            }
        )
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
