package com.example.talentHunters.di.modules

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talentHunters.ui.base.BaseFragment
import dagger.Module
import dagger.Provides

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

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