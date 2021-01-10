package com.example.chartsSample.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chartsSample.data.repository.UserRepository
import com.example.chartsSample.ui.base.BaseActivity
import com.example.chartsSample.ui.login.LoginActivityViewModel
import com.example.chartsSample.ui.main.MainActivityViewModel
import com.example.chartsSample.ui.splash.SplashActivityViewModel
import com.example.chartsSample.utils.ViewModelProviderFactory
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashActivityViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashActivityViewModel::class) {
            SplashActivityViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(SplashActivityViewModel::class.java)

    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginActivityViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(LoginActivityViewModel::class) {
            LoginActivityViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(LoginActivityViewModel::class.java)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): MainActivityViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainActivityViewModel::class) {
            MainActivityViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(MainActivityViewModel::class.java)

}