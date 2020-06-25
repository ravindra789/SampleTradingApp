package com.example.challengers.di.components

import com.example.challengers.di.FragmentScope
import com.example.challengers.di.modules.FragmentModule
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