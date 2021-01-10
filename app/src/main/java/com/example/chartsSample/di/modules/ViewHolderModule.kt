package com.example.chartsSample.di.modules

import androidx.lifecycle.LifecycleRegistry
import com.example.chartsSample.di.ViewModelScope
import com.example.chartsSample.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

/**
 * Created by RavindraP on 25 June 2020
 */
@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}