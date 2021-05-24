package com.duonghb.testbitrise.ui.home

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.view.menu.MenuBuilder
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
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(it.url))
            }
        )
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun init() {
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
            }
        )
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
