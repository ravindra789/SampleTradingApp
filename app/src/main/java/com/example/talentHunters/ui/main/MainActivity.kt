package com.example.talentHunters.ui.main

import android.os.Bundle
import com.example.talentHunters.R
import com.example.talentHunters.di.components.ActivityComponent
import com.example.talentHunters.ui.base.BaseActivity

/**
 * Created by RavindraP on 30 June 2020
 */
class MainActivity : BaseActivity<MainActivityViewModel>()  {

    override fun provideLayoutId() = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {}

    override fun logScreenFlowEventForAnalytics() {}
}