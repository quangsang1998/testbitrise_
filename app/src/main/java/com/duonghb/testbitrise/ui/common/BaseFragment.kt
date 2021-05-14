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

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    @get: LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var binding: DB

    protected val safeActivity by lazy {
        requireActivity() as AppCompatActivity
    }

    open fun init() {
    }

    open fun initUi() {
    }

    open fun registerListeners() {
    }

    open fun registerLivadataListeners() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initUi()
        registerListeners()
        registerLivadataListeners()
    }
}
