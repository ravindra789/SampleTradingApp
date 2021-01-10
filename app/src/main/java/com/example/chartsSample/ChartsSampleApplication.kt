package com.example.chartsSample

import androidx.multidex.MultiDexApplication
import com.example.chartsSample.di.components.ApplicationComponent
import com.example.chartsSample.di.components.DaggerApplicationComponent
import com.example.chartsSample.di.modules.ApplicationModule

/**
 * Created by RavindraP on 25 June 2020
 */
class ChartsSampleApplication : MultiDexApplication() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

}