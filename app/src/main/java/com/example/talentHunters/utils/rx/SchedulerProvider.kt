package com.example.talentHunters.utils.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * Created by RavindraP on 25 June 2020
 */
@Singleton
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}