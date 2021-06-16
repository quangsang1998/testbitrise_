package com.duonghb.testbitrise.ui.news

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.domain.usecase.DeleteNewsHistoryUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.domain.usecase.SaveNewsHistoryUseCase
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
class NewsViewModel @Inject constructor(
    private val deleteNewsHistoryUseCase: DeleteNewsHistoryUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val saveNewsHistoryUseCase: SaveNewsHistoryUseCase
) : BaseViewModel(), NewListItemViewModel.Listener {

    val onEvent: LiveData<Event> get() = _onEvent
    private val _onEvent = NoCacheMutableLiveData<Event>()

    val fetchedNews: LiveData<List<NewListItemViewModel>> get() = _fetchedNews
    private val _fetchedNews = MutableLiveData<List<NewListItemViewModel>>()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.map {
        if (it) View.VISIBLE else View.GONE
    }

    val swipeRefreshing: LiveData<Boolean> get() = _swipeRefreshing
    private val _swipeRefreshing = MutableLiveData<Boolean>()

    init {
        _loading.postValue(true)
        loadData()
    }

    private fun loadData() {
        getNewsListUseCase.invoke(Constant.API_KEY)
            .map {
                it.results.map { NewListItemViewModel(it, this) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doFinally {
                _loading.postValue(false)
                _swipeRefreshing.postValue(false)
            }
            .subscribeBy(
                onSuccess = {
                    _fetchedNews.postValue(it)
                },
                onError = {
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    fun swipeRefreshingData() {
        _swipeRefreshing.postValue(true)
        loadData()
    }

    private fun saveNew(model: NewsModelData) {
        deleteNewsHistoryUseCase.invoke(model.url)
            .andThen(
                saveNewsHistoryUseCase.invoke(model)
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Timber.e("onComplete")
                },
                onError = {
                    Timber.e("Error")
                }
            )
            .addTo(disposables)
    }

    override fun onItemClick(newItem: NewsModelData) {
        saveNew(newItem.copy(time = System.currentTimeMillis()))
        _onEvent.setValue(Event.ClickedItem(newItem))
    }

    sealed class Event {
        object ClickedClose : Event()
        class ClickedItem(val newItem: NewsModelData) : Event()
    }
}
