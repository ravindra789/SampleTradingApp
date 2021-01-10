package com.example.chartsSample.data.remote

import com.google.gson.JsonArray
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Singleton
interface NetworkService {
    @GET(Endpoints.HISTORY_DATA)
    fun submitSellerFeedback(
        @Query("interval") interval: Int
    ): Single<JsonArray>
}