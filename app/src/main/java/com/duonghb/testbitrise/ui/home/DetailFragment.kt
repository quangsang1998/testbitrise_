package com.duonghb.testbitrise.ui.home

import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.DetailFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.detail_fragment

    override fun onResume() {
        super.onResume()
        safeActivity.supportActionBar?.setTitle(R.string.title_news_detail)
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun init() {
        val url = args.url

        newsDetailWebView.settings.loadsImagesAutomatically
        newsDetailWebView.settings.javaScriptEnabled
        newsDetailWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        newsDetailWebView.loadUrl(url)
        newsDetailWebView.webViewClient = WebViewClient()
    }

    override fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerLivedataListeners() {
    }
}
