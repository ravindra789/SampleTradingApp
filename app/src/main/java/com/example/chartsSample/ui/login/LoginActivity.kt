package com.example.chartsSample.ui.login

import android.content.Intent
import android.os.Bundle
import com.example.chartsSample.R
import com.example.chartsSample.di.components.ActivityComponent
import com.example.chartsSample.ui.base.BaseActivity
import com.example.chartsSample.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by RavindraP on 30 June 2020
 */
class LoginActivity : BaseActivity<LoginActivityViewModel>()  {

    override fun provideLayoutId() = R.layout.activity_login

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        loginToolbar.title = resources.getString(R.string.log_in)
        loginToolbar.setTitleTextAppearance(this, R.style.WhiteToolBarTitleMedium);
        setClickListeners()
    }

    private fun setClickListeners() {
        btn_sign_in.setOnClickListener {
            callNextActivity()
        }
    }

    private fun callNextActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun logScreenFlowEventForAnalytics() {}
}