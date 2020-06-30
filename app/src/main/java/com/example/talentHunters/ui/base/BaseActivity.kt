package com.example.talentHunters.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.talentHunters.TalentHunterApplication
import com.example.talentHunters.di.components.ActivityComponent
import com.example.talentHunters.di.components.DaggerActivityComponent
import com.example.talentHunters.di.modules.ActivityModule
import com.example.talentHunters.utils.display.Toaster
import javax.inject.Inject

/**
 * Created by RavindraP on 25 June 2020
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as TalentHunterApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    fun showMessage(message: String) = Toaster.show(applicationContext, message)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun logScreenFlowEventForAnalytics()
}