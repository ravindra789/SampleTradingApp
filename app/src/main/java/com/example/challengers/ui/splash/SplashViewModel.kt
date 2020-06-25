package com.example.challengers.ui.splash

import com.example.challengers.data.repository.UserRepository
import com.example.challengers.ui.base.BaseViewModel
import com.example.challengers.utils.network.NetworkHelper
import com.example.challengers.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 25 June 2020
 */
class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}
}