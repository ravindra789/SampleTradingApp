package com.example.challengers.data.repository

import com.example.challengers.data.local.prefs.UserPreferences
import com.example.challengers.data.remote.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val userPreferences: UserPreferences) {

    private var userId: Long? = null

}