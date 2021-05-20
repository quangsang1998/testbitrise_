package com.duonghb.testbitrise.ui.home

import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.DetailFragmentBinding
import com.duonghb.testbitrise.ui.common.BaseFragment

class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.detail_fragment

    override fun onResume() {
        super.onResume()
        safeActivity.supportActionBar?.setTitle(R.string.title_news_detail)
    }

    override fun init() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (!findNavController().popBackStack()) {
                    safeActivity.finish()
                }
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        safeActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        safeActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerLivedataListeners() {
    }
}
