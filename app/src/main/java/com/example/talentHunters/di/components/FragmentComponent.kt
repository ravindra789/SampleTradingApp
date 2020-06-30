package com.example.talentHunters.di.components

import com.example.talentHunters.di.FragmentScope
import com.example.talentHunters.di.modules.FragmentModule
import dagger.Component

/**
 * Created by RavindraP on 25 June 2020
 */
@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    //fun inject(fragment: DummiesFragment)

   // fun inject(fragment: HomeFragment)

}