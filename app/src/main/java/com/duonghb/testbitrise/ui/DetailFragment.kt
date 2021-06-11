package com.duonghb.testbitrise.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.duonghb.testbitrise.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailFragment = R.layout.detail_fragment

    private lateinit var binding: ViewDataBinding

    private val safeActivity by lazy {
        requireActivity() as AppCompatActivity
    }

    override fun onResume() {
        super.onResume()
        safeActivity.supportActionBar?.setTitle(R.string.title_news_detail)
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, detailFragment, container, false)
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
        val url = args.url

        newsDetailWebView.apply {
            settings.loadsImagesAutomatically
            settings.javaScriptEnabled
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            loadUrl(url)
            webViewClient = WebViewClient()
        }
    }

    private fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun registerLivedataListeners() {
    }

    companion object {

        fun newInstance() = DetailFragment()
    }
}
