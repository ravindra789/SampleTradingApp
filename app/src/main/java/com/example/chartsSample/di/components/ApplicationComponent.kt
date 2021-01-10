package com.example.chartsSample.di.components

import android.app.Application
import android.content.Context
import com.example.chartsSample.ChartsSampleApplication
import com.example.chartsSample.data.remote.NetworkService
import com.example.chartsSample.data.repository.UserRepository
import com.example.chartsSample.di.ApplicationContext
import com.example.chartsSample.di.modules.ApplicationModule
import com.example.chartsSample.data.local.prefs.SharedPreferencesManager
import com.example.chartsSample.ui.companyDetails.CompanyDetailsRepository
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: ChartsSampleApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    /**
     * These methods are written in ApplicationComponent because the instance of
     * NetworkService is singleton and is maintained in the ApplicationComponent's implementation by Dagger
     * For NetworkService singleton instance to be accessible to say DummyActivity's DummyViewModel
     * this ApplicationComponent must expose a method that returns NetworkService instance
     * This method will be called when NetworkService is injected in DummyViewModel.
     * Also, in ActivityComponent you can find dependencies = [ApplicationComponent::class] to link this relationship
     */
    fun getNetworkService(): NetworkService

    //fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferencesManager

    fun getNetworkHelper(): NetworkHelper

    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [NetworkService -> Nothing is required],
     *  [DatabaseService -> Nothing is required],
     *  [UserPreferences -> [SharedPreferences -> provided by the function provideSharedPreferences in ApplicationModule class]]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */
    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

}