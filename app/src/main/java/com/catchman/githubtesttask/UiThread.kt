package com.example.catchman.todo

//import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ujujzk on 20.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Singleton
class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}