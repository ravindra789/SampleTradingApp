package com.example.talentHunters.di.components

import com.example.talentHunters.di.ViewModelScope
import com.example.talentHunters.di.modules.ViewHolderModule
import dagger.Component

/**
 * Created by RavindraP on 25 June 2020
 */
@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    //fun inject(viewHolder: DummyItemViewHolder)

    //fun inject(viewHolder: PostItemViewHolder)
}