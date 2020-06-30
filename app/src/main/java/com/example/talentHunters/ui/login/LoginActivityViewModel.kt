package com.example.talentHunters.ui.login

import com.example.talentHunters.data.repository.UserRepository
import com.example.talentHunters.ui.base.BaseViewModel
import com.example.talentHunters.utils.network.NetworkHelper
import com.example.talentHunters.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 30 June 2020
 */
class LoginActivityViewModel (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}
}