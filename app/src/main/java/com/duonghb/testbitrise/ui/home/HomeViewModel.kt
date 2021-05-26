package com.duonghb.testbitrise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.domain.usecase.GetNewsHistoryListUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.domain.usecase.SaveNewsHistoryUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import com.duonghb.testbitrise.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getNewsHistoryListUseCase: GetNewsHistoryListUseCase,
    private val saveNewsHistoryUseCase: SaveNewsHistoryUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val loadNewsCompleted: LiveData<NewsModel> get() = _loadNewsCompleted
    private val _loadNewsCompleted = MutableLiveData<NewsModel>()

    val loadNewsHistoryListDatabaseCompleted: LiveData<List<HistoryModelData>> get() = _loadNewsHistoryListDatabaseCompleted
    private val _loadNewsHistoryListDatabaseCompleted = MutableLiveData<List<HistoryModelData>>()

    val saveNewsHistoryDatabaseCompleted: LiveData<Boolean> get() = _saveNewsHistoryDatabaseCompleted
    private val _saveNewsHistoryDatabaseCompleted = MutableLiveData<Boolean>()

    fun loadData() {
        disposables.add(
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
        )
    }

    fun saveNewsModelDatabase(model: NewsModelData) {
        disposables.add(
            saveNewsHistoryUseCase.invoke(model)
                .observeOn(schedulerProvider.io())
                .subscribeOn(schedulerProvider.io())
                .subscribeBy(
                    onComplete = {
                        _saveNewsHistoryDatabaseCompleted.postValue(true)
                        Timber.i("save news success")
                    },
                    onError = {
                        Timber.e("Error")
                    }
                )
        )
    }

    fun getNewsHistoryListDatabase() {
        disposables.add(
            getNewsHistoryListUseCase.invoke()
                .observeOn(schedulerProvider.io())
                .subscribeOn(schedulerProvider.io())
                .subscribeBy(
                    onSuccess = {
                        _loadNewsHistoryListDatabaseCompleted.postValue(it)
                    },
                    onError = {
                        Timber.e("Error")
                    }
                )
        )
    }
}

