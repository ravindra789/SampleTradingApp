package com.example.chartsSample.di.components

import com.example.chartsSample.di.FragmentScope
import com.example.chartsSample.di.modules.FragmentModule
import com.example.chartsSample.ui.companyDetails.CompanyDetailsChartFragment
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
    fun inject(fragment: CompanyDetailsChartFragment)
}