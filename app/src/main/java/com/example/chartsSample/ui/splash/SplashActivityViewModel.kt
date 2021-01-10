package com.example.chartsSample.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.chartsSample.data.repository.UserRepository
import com.example.chartsSample.ui.base.BaseViewModel
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 25 June 2020
 */
class SplashActivityViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {}

    fun getLoginFlow(){
        isUserLoggedIn.postValue(userRepository.decideIfUserLoggedIn())
    }


}