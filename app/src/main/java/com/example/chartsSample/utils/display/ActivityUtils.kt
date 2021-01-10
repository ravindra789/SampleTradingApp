package com.example.chartsSample.utils.display

import android.app.Activity
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle

/**
 * Created by RavindraP on 08 January 2021
 */
fun FragmentActivity.addFragment(frameId: Int,
                                 fragment: Fragment,
                                 tag: String? = fragment::class.java.simpleName,
                                 extraFunc: (FragmentTransaction.() -> FragmentTransaction)? = null) {
    extraFunc?.let {
        supportFragmentManager.inTransaction { add(frameId, fragment, tag).extraFunc() }
    } ?: run {
        supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
    }
}

fun FragmentActivity.replaceFragment(frameId: Int,
                                     fragment: Fragment,
                                     tag: String? = fragment::class.java.simpleName,
                                     extraFunc: (FragmentTransaction.() -> FragmentTransaction)? = null) {
    extraFunc?.let {
        supportFragmentManager.inTransaction { replace(frameId, fragment, tag).extraFunc() }
    } ?: run {
        supportFragmentManager.inTransaction { replace(frameId, fragment, tag) }
    }
}

fun FragmentActivity.hideSoftKeyboard() {
    val inputManager = if (Build.VERSION.SDK_INT >= 23)
        getSystemService(InputMethodManager::class.java)
    else getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let { focus -> inputManager.hideSoftInputFromWindow(focus.windowToken, 0) }
}

fun FragmentActivity.isActivityInForeground() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)