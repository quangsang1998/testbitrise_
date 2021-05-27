package com.duonghb.testbitrise.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
}
