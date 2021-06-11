package com.duonghb.testbitrise.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.duonghb.testbitrise.domain.usecase.*
import com.duonghb.testbitrise.ui.common.BaseViewModel
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
) : BaseViewModel() {

    val loadNewsHistoryListDatabaseCompleted: LiveData<List<HistoryModelData>> get() = _loadNewsHistoryListDatabaseCompleted
    private val _loadNewsHistoryListDatabaseCompleted = MutableLiveData<List<HistoryModelData>>()

    fun getNewsHistoryListDatabase() {
        getNewsHistoryListUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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
