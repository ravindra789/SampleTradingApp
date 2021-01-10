package com.example.chartsSample.di.components

import com.example.chartsSample.di.ActivityScope
import com.example.chartsSample.di.modules.ActivityModule
import com.example.chartsSample.ui.login.LoginActivity
import com.example.chartsSample.ui.main.MainActivity
import com.example.chartsSample.ui.splash.SplashActivity
import dagger.Component

/**
 * Created by RavindraP on 25 June 2020
 */
@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: MainActivity)
}