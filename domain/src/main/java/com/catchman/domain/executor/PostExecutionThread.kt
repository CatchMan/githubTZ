package com.catchman.domain.executor

import io.reactivex.Scheduler



interface PostExecutionThread {
    val scheduler: Scheduler
}
