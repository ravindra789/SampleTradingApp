package com.example.challengers.di.components

import com.example.challengers.di.ActivityScope
import com.example.challengers.di.modules.ActivityModule
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

    //fun inject(activity: SplashActivity)

    //fun inject(activity: LoginActivity)

    //fun inject(activity: DummyActivity)

    //fun inject(activity: MainActivity)
}