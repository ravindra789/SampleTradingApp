package com.example.chartsSample.data.repository

import com.example.chartsSample.data.remote.NetworkService
import com.example.chartsSample.utils.common.Config
import com.example.chartsSample.data.local.prefs.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val userPreferences: SharedPreferencesManager
) {

    private var userId: Long? = null

    fun decideIfUserLoggedIn(): Boolean{
        return userPreferences.getBoolean(Config.IS_USER_LOGGED_IN)
    }

}