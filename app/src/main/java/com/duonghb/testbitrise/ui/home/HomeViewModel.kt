package com.duonghb.testbitrise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.domain.usecase.GetNewsImageListUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import com.duonghb.testbitrise.util.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getNewsImageListUseCase: GetNewsImageListUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val loadNewsCompleted: LiveData<NewsModel> get() = _loadNewsCompleted
    private val _loadNewsCompleted = MutableLiveData<NewsModel>()
    val loadNewsImageCompleted: LiveData<NewsModelData> get() = _LoadNewsImageCompleted
    private val _LoadNewsImageCompleted = MutableLiveData<NewsModelData>()

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

    fun loadImage() {
        disposables.add(
            getNewsImageListUseCase.invoke()
                .observeOn(schedulerProvider.io())
                .subscribeOn(schedulerProvider.io())
                .subscribeBy(
                    onSuccess = {
                        _LoadNewsImageCompleted.postValue(it)
                    },
                    onError = {
                        Timber.e("Error")
                    }
                )
        )
    }
}
