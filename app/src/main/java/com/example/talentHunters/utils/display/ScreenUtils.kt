package com.example.talentHunters.utils.display

import android.content.res.Resources

/**
 * Created by RavindraP on 25 June 2020
 */
object ScreenUtils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels
}