package com.example.talentHunters.di.modules

import androidx.lifecycle.LifecycleRegistry
import com.example.talentHunters.di.ViewModelScope
import com.example.talentHunters.ui.base.BaseItemViewHolder
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