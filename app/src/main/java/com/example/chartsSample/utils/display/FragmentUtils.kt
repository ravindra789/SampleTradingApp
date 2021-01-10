package com.example.chartsSample.utils.display

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by RavindraP on 08 January 2021
 */
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun Fragment.addFragment(frameId: Int,
                         fragment: Fragment,
                         tag: String? = fragment::class.java.simpleName,
                         extraFunc: (FragmentTransaction.() -> FragmentTransaction)? = null) {
    activity?.addFragment(frameId, fragment, tag, extraFunc)
}

fun Fragment.replaceFragment(frameId: Int,
                             fragment: Fragment,
                             tag: String? = fragment::class.java.simpleName,
                             extraFunc: (FragmentTransaction.() -> FragmentTransaction)? = null) {
    activity?.replaceFragment(frameId, fragment, tag, extraFunc)
}