package com.example.talentHunters.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.talentHunters.data.repository.UserRepository
import com.example.talentHunters.ui.base.BaseViewModel
import com.example.talentHunters.utils.network.NetworkHelper
import com.example.talentHunters.utils.rx.SchedulerProvider
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