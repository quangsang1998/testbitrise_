package com.duonghb.testbitrise.util.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun io(): Scheduler = Schedulers.io()
}
