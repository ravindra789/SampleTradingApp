package com.example.challengers.di

import javax.inject.Qualifier

/**
 * Created by RavindraP on 25 June 2020
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext