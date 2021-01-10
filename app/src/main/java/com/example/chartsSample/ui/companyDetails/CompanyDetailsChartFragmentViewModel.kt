package com.example.chartsSample.ui.companyDetails

import androidx.lifecycle.MutableLiveData
import com.example.chartsSample.data.repository.UserRepository
import com.example.chartsSample.ui.base.BaseViewModel
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.SchedulerProvider
import com.google.gson.JsonArray
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 08 January 2021
 */
class CompanyDetailsChartFragmentViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val companyDetailsRepository: CompanyDetailsRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val historicalData: MutableLiveData<JsonArray> = MutableLiveData()

    fun getHistoricalData(interval: Int) {
        compositeDisposable.add(
            companyDetailsRepository
                .getHistoryData(interval)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    historicalData.postValue(it)
                }, {
                    handleNetworkError(it)
                })
        )
    }

}