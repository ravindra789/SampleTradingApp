package com.example.chartsSample.ui.main

import android.os.Bundle
import com.example.chartsSample.R
import com.example.chartsSample.di.components.ActivityComponent
import com.example.chartsSample.ui.base.BaseActivity
import com.example.chartsSample.ui.companyDetails.CompanyDetailsChartFragment
import com.example.chartsSample.utils.display.addFragment

/**
 * Created by RavindraP on 30 June 2020
 */
class MainActivity : BaseActivity<MainActivityViewModel>()  {

    companion object{
        const val TAG = "MainActivity"
    }

    override fun provideLayoutId() = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        addFragment(R.id.main_activity_fragment_container, CompanyDetailsChartFragment.newInstance(), TAG)
    }

    override fun logScreenFlowEventForAnalytics() {}
}