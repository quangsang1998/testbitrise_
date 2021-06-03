package com.duonghb.testbitrise.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.domain.usecase.DeleteNewsHistoryUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.domain.usecase.SaveNewsHistoryUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import com.duonghb.testbitrise.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val deleteNewsHistoryUseCase: DeleteNewsHistoryUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val saveNewsHistoryUseCase: SaveNewsHistoryUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val saveNewsHistoryDatabaseCompleted: LiveData<Boolean> get() = _saveNewsHistoryDatabaseCompleted
    private val _saveNewsHistoryDatabaseCompleted = MutableLiveData<Boolean>()

    val loadNewsCompleted: LiveData<NewsModel> get() = _loadNewsCompleted
    private val _loadNewsCompleted = MutableLiveData<NewsModel>()

    fun loadData() {
        getNewsListUseCase.invoke()
            .observeOn(schedulerProvider.io())
            .subscribeOn(schedulerProvider.io())
            .subscribeBy(
                onSuccess = {
                    _loadNewsCompleted.postValue(it)
                },
                onError = {
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    fun saveNewsModelDatabase(model: NewsModelData) {
        deleteNewsHistoryUseCase.invoke(model.url)
            .concatWith {
                saveNewsHistoryUseCase.invoke(model)
            }
            .observeOn(schedulerProvider.io())
            .subscribeOn(schedulerProvider.io())
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
}
