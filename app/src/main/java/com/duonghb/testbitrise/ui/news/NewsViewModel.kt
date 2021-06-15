package com.duonghb.testbitrise.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.domain.usecase.DeleteNewsHistoryUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.domain.usecase.SaveNewsHistoryUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val deleteNewsHistoryUseCase: DeleteNewsHistoryUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val saveNewsHistoryUseCase: SaveNewsHistoryUseCase
) : BaseViewModel() {

    private val _saveNewsHistoryDatabaseCompleted = MutableLiveData<Boolean>()

    val loadNewsCompleted: LiveData<NewsModel> get() = _loadNewsCompleted
    private val _loadNewsCompleted = MutableLiveData<NewsModel>()

    val loadNewsCompletedProgressBar: LiveData<Boolean> get() = _loadNewsCompletedProgressBar
    private val _loadNewsCompletedProgressBar = MutableLiveData<Boolean>()

    val swipeRefreshing: LiveData<Boolean> get() = _swipeRefreshing
    private val _swipeRefreshing = MutableLiveData<Boolean>()

    fun loadData() {
        getNewsListUseCase.invoke(Constant.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    _loadNewsCompleted.postValue(it)
                    _loadNewsCompletedProgressBar.postValue(true)
                    _swipeRefreshing.postValue(false)
                },
                onError = {
                    _swipeRefreshing.postValue(false)
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    fun swipeRefreshingData() {
        _swipeRefreshing.postValue(true)
        loadData()
    }

    fun saveNewsModelDatabase(model: NewsModelData) {
        deleteNewsHistoryUseCase.invoke(model.url)
            .andThen(
                saveNewsHistoryUseCase.invoke(model)
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    _saveNewsHistoryDatabaseCompleted.postValue(true)
                },
                onError = {
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    init {
        loadData()
    }
}
