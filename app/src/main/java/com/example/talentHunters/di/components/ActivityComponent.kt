package com.example.talentHunters.di.components

import com.example.talentHunters.di.ActivityScope
import com.example.talentHunters.di.modules.ActivityModule
import com.example.talentHunters.ui.login.LoginActivity
import com.example.talentHunters.ui.main.MainActivity
import com.example.talentHunters.ui.splash.SplashActivity
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