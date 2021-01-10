package com.example.chartsSample.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chartsSample.data.repository.UserRepository
import com.example.chartsSample.ui.base.BaseFragment
import com.example.chartsSample.ui.companyDetails.CompanyDetailsChartFragmentViewModel
import com.example.chartsSample.ui.companyDetails.CompanyDetailsRepository
import com.example.chartsSample.utils.ViewModelProviderFactory
import com.example.chartsSample.utils.network.NetworkHelper
import com.example.chartsSample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideDummiesViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        companyDetailsRepository: CompanyDetailsRepository
    ): CompanyDetailsChartFragmentViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(CompanyDetailsChartFragmentViewModel::class) {
                CompanyDetailsChartFragmentViewModel(
                    schedulerProvider,
                    compositeDisposable,
                    networkHelper,
                    userRepository,
                    companyDetailsRepository
                )
            }
        ).get(CompanyDetailsChartFragmentViewModel::class.java)


    /*   @Provides
       fun provideDummiesViewModel(
           schedulerProvider: SchedulerProvider,
           compositeDisposable: CompositeDisposable,
           networkHelper: NetworkHelper,
           dummyRepository: DummyRepository
       ): DummiesViewModel =
           ViewModelProviders.of(fragment,
               ViewModelProviderFactory(DummiesViewModel::class) {
                   DummiesViewModel(schedulerProvider, compositeDisposable, networkHelper, dummyRepository)
               }
           ).get(DummiesViewModel::class.java)

       @Provides
       fun provideDummiesAdapter() = DummiesAdapter(fragment.lifecycle, ArrayList())

       @Provides
       fun providePostsAdapter() = PostsAdapter(fragment.lifecycle, ArrayList())*/


}