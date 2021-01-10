package com.example.chartsSample.di.modules

import android.app.Application
import android.content.Context
import com.example.chartsSample.ChartsSampleApplication
import com.example.chartsSample.BuildConfig
import com.example.chartsSample.data.remote.NetworkService
import com.example.chartsSample.data.remote.Networking
import com.example.chartsSample.di.ApplicationContext
import com.example.chartsSample.data.local.prefs.SharedPreferencesManager
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.RxSchedulerProvider
import com.example.chartsSample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class ApplicationModule(private val application: ChartsSampleApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferencesManager = SharedPreferencesManager.create(application)

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
   /* @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "bootcamp-instagram-project-db"
        ).build()*/

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}