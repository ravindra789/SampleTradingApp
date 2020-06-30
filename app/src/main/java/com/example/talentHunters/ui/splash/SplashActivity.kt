package com.example.talentHunters.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.talentHunters.R
import com.example.talentHunters.di.components.ActivityComponent
import com.example.talentHunters.ui.base.BaseActivity
import com.example.talentHunters.ui.login.LoginActivity
import com.example.talentHunters.ui.main.MainActivity


/**
 * Created by RavindraP on 25 June 2020
 */
class SplashActivity : BaseActivity<SplashActivityViewModel>()  {

    companion object{
        const val SPLASH_TIME_OUT = 2000L
    }

    override fun provideLayoutId() = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({ checkLoginStatus() }, SPLASH_TIME_OUT)
    }

    private fun checkLoginStatus() {
        viewModel.getLoginFlow()
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.isUserLoggedIn.observe(this, Observer {
            if (it) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }


    override fun logScreenFlowEventForAnalytics() {}
}