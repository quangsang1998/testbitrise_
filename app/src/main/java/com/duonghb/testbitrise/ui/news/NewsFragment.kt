package com.duonghb.testbitrise.ui.news

import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.news_fragment

    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

    private val viewModelNews: NewsViewModel by viewModels()

    private val newsAdapter by lazy {
        NewsAdapter(
            clickItemCallback = {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                        it.url
                    )
                )
                viewModelNews.saveNewsModelDatabase(it)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                newsSwipeRefresh.isRefreshing = true
                viewModelNews.loadData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        inAnimation = AlphaAnimation(0f, 1f)
        inAnimation.duration = 200
        progressLoading.animation = inAnimation
        progressLoading.visibility = View.VISIBLE
    }

    override fun init() {
        setHasOptionsMenu(true)

        outAnimation = AlphaAnimation(1f, 0f)
        outAnimation.duration = 200
        progressLoading.animation = outAnimation
        progressLoading.visibility = View.GONE
        viewModelNews.loadData()

        newsSwipeRefresh.setOnRefreshListener {
            viewModelNews.loadData()
        }
    }

    override fun initUi() {
        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.setItemViewCacheSize(10)
    }

    override fun registerLivedataListeners() {
        viewModelNews.loadNewsCompleted.observe(
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
