package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsFragment = R.layout.news_fragment

    private lateinit var binding: NewsFragmentBinding

    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation

    private val newsViewModel: NewsViewModel by viewModels()

    private val newsAdapter by lazy {
        NewsAdapter(
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

    override fun onStart() {
        super.onStart()
        inAnimation = AlphaAnimation(0f, 1f)
        progressLoading.animation = inAnimation
        progressLoading.visibility = View.VISIBLE
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
        outAnimation = AlphaAnimation(1f, 0f)
        progressLoading.animation = outAnimation

        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.setItemViewCacheSize(20)
    }

    private fun registerLiveDataListener() {
        newsViewModel.loadNewsCompleted.observe(
            viewLifecycleOwner,
            Observer {
                progressLoading.visibility = View.GONE
                newsAdapter.setItems(it)
            }
        )
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
