package com.example.talentHunters.di.modules

import android.app.Application
import android.content.Context
import com.example.talentHunters.TalentHunterApplication
import com.example.talentHunters.BuildConfig
import com.example.talentHunters.data.remote.NetworkService
import com.example.talentHunters.data.remote.Networking
import com.example.talentHunters.di.ApplicationContext
import com.example.talentHunters.data.local.prefs.SharedPreferencesManager
import com.example.talentHunters.utils.network.NetworkHelper
import com.example.talentHunters.utils.rx.RxSchedulerProvider
import com.example.talentHunters.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class ApplicationModule(private val application: TalentHunterApplication) {

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