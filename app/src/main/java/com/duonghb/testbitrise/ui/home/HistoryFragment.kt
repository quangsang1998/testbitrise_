package com.duonghb.testbitrise.ui.home

import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FragmentHistoryBinding
import com.duonghb.testbitrise.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_history

    override fun init() {
        super.init()
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
