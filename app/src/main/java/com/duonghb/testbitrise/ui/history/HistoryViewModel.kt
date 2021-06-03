package com.duonghb.testbitrise.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.duonghb.testbitrise.domain.usecase.*
import com.duonghb.testbitrise.ui.common.BaseViewModel
import com.duonghb.testbitrise.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getNewsHistoryListUseCase: GetNewsHistoryListUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val loadNewsHistoryListDatabaseCompleted: LiveData<List<HistoryModelData>> get() = _loadNewsHistoryListDatabaseCompleted
    private val _loadNewsHistoryListDatabaseCompleted = MutableLiveData<List<HistoryModelData>>()

    fun getNewsHistoryListDatabase() {
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
            .addTo(disposables)
    }
}
