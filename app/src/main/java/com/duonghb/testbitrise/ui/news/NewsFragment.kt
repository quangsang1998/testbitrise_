package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.duonghb.testbitrise.ui.home.HomeFragmentDirections
import com.duonghb.testbitrise.ui.news.NewsViewModel.Event
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.news_fragment) {

    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val binding = NewsFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.newsRecyclerView.adapter = adapter

        viewModel.fetchedNews.observe(viewLifecycleOwner, ::renderItems)
        viewModel.onEvent.observe(viewLifecycleOwner, ::handleEvent)
    }

    private fun renderItems(news: List<NewListItemViewModel>) {
        adapter.updateAsync(news.map { NewListItem(it) })
    }

    private fun handleEvent(event: Event) {
        when (event) {
            Event.ClickedClose -> {
                // sample use sealed
            }
            is Event.ClickedItem -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationNewsDetail(
                        event.newItem.url
                    )
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                viewModel.swipeRefreshingData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
