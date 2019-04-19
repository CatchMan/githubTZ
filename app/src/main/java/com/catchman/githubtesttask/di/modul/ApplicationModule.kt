package com.catchman.githubtesttask.di.modul

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.catchman.githubtesttask.util.dataLogger
import com.catchman.githubtesttask.util.domainLogger
import com.catchman.githubtesttask.util.netLogger
import com.catchman.data.DataLogger
import com.catchman.data.interceptor.LoggingInterceptor
import com.catchman.data.github.service.GitHubService
import com.catchman.domain.DomainLogger
import com.catchman.githubtesttask.GitHubApp
import com.example.data.JobExecutor
import com.catchman.data.github.GitHubGatewayImpl
import com.catchman.data.github.service.GitHubApi
import com.catchman.data.github.service.GitHubServiceImpl
import com.catchman.domain.executor.PostExecutionThread
import com.catchman.domain.executor.ThreadExecutor
import com.example.catchman.todo.UiThread
import com.catchman.data.ApiConst
import com.catchman.domain.gateway.GitHubGateway
import com.example.data.AppDatabase
import com.catchman.data.github.storage.GitHubStorage
import com.catchman.data.github.storage.GitHubStorageImpl
import com.catchman.githubtesttask.di.ApplicationContext
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = app

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    fun provideDataLogger(): DataLogger = dataLogger()

    @Provides
    fun provideDomainLogger(): DomainLogger = domainLogger()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = LoggingInterceptor(object : LoggingInterceptor.Logger {
            override fun log(message: String) = netLogger(message)
        }, app).setLevel(LoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logging)
            .addInterceptor(ChuckInterceptor(GitHubApp.context))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideCountryApi(client: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
                .baseUrl(ApiConst.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSferaService(countryService: GitHubServiceImpl): GitHubService = countryService

    @Provides
    @Singleton
    fun provideSferaStorage(gitHubStorage: GitHubStorageImpl): GitHubStorage = gitHubStorage

    @Provides
    @Singleton
    fun provideSferaGateway(countryGateway: GitHubGatewayImpl): GitHubGateway = countryGateway

    @Provides
    @Singleton
    fun provideRoomDatabase() =
            Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "country-database")
                    .fallbackToDestructiveMigration()
                    .build()

}