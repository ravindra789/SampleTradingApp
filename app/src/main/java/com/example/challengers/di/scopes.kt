package com.example.challengers.di

import javax.inject.Scope

/**
 * Created by RavindraP on 25 June 2020
 */
@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ViewModelScope