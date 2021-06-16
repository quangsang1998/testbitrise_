package com.duonghb.testbitrise.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.duonghb.testbitrise.domain.usecase.GetNewsHistoryListUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import com.duonghb.testbitrise.ui.common.NoCacheMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getNewsHistoryListUseCase: GetNewsHistoryListUseCase
) : BaseViewModel(), HistoryListItemViewModel.Listener {

    val onEventHistory: LiveData<EventHistory> get() = _onEventHistory
    private val _onEventHistory = NoCacheMutableLiveData<EventHistory>()

    val loadHistories: LiveData<List<HistoryListItemViewModel>> get() = _loadHistories
    private val _loadHistories = MutableLiveData<List<HistoryListItemViewModel>>()

    fun getNewsHistoryListDatabase() {
        getNewsHistoryListUseCase.invoke()
            .map { it.map { HistoryListItemViewModel(it, this) } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    _loadHistories.postValue(it)
                },
                onError = {
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    override fun onItemHistoryClick(historyItem: HistoryModelData) {
        _onEventHistory.setValue(EventHistory.ClickedItem(historyItem))
    }

    sealed class EventHistory {
        object ClickedClose : EventHistory()
        class ClickedItem(val historyItem: HistoryModelData) : EventHistory()
    }
}
