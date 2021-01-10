package com.example.chartsSample.ui.companyDetails

import com.example.chartsSample.data.remote.NetworkService
import com.google.gson.JsonArray
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by RavindraP on 09 January 2021
 */
class CompanyDetailsRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun getHistoryData(interval: Int): Single<JsonArray> {
        return networkService.submitSellerFeedback(interval)
    }
}