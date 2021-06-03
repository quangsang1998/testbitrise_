package com.duonghb.testbitrise.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<binding : ViewDataBinding> : Fragment() {
    @get: LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var binding: binding

    protected val safeActivity by lazy {
        requireActivity() as AppCompatActivity
    }

    open fun load() {
    }

    open fun finishLoad() {
    }

    abstract fun init()

    abstract fun initUi()

    abstract fun registerLivedataListeners()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        load()
        finishLoad()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initUi()
        registerLivedataListeners()
    }
}
