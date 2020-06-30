package com.example.talentHunters.ui.base

import androidx.lifecycle.MutableLiveData
import com.example.talentHunters.utils.network.NetworkHelper
import com.example.talentHunters.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 25 June 2020
 */
abstract class BaseItemViewModel<T : Any>(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val data: MutableLiveData<T> = MutableLiveData()

    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}