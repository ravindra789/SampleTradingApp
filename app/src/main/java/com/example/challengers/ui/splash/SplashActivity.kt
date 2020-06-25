package com.example.challengers.ui.splash

import android.os.Bundle
import com.example.challengers.R
import com.example.challengers.di.components.ActivityComponent
import com.example.challengers.ui.base.BaseActivity

/**
 * Created by RavindraP on 25 June 2020
 */
class SplashActivity : BaseActivity<SplashViewModel>()  {

    override fun provideLayoutId() = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {}

    override fun setupObservers() {
        super.setupObservers()
    }

    override fun logScreenFlowEventForAnalytics() {}
}