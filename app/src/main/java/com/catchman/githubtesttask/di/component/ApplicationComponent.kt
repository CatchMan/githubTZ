package com.catchman.githubtesttask.di.component

import android.app.Application
import android.content.Context
import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.catchman.githubtesttask.GitHubApp
import com.catchman.domain.gateway.GitHubGateway
import com.catchman.githubtesttask.di.ApplicationContext
import com.catchman.githubtesttask.di.modul.ApplicationModule
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: GitHubApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread

    fun countryGateway(): GitHubGateway

}