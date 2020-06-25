package com.example.challengers

import android.app.Application
import com.example.challengers.di.components.ApplicationComponent
import com.example.challengers.di.components.DaggerApplicationComponent
import com.example.challengers.di.modules.ApplicationModule

/**
 * Created by RavindraP on 25 June 2020
 */
class BountyApplication : Application() {

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