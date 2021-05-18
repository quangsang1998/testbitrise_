package com.duonghb.testbitrise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class HomeViewModel(
    private val getNewsListUseCase: GetNewsListUseCase
) : BaseViewModel() {

    val loadNewsCompleted: LiveData<List<NewsModel>> get() = _loadNewsCompleted
    private val _loadNewsCompleted = MutableLiveData<List<NewsModel>>()

    fun loadData() {
        disposables.add(
            getNewsListUseCase()
                .subscribeBy(
                    onSuccess = {
                        _loadNewsCompleted.postValue(it)
                    },
                    onError = {
                        Timber.e("Error")
                    }
                )
        )
    }
}
