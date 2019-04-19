package com.catchman.data.github.service

import com.catchman.data.github.service.CommonUser
import io.reactivex.Single
import retrofit2.http.*


interface GitHubApi {

    @GET("users")
    fun getCommonUsers(@Query("since") since: Long): Single<List<CommonUser>>

    @GET("users/{login}")
    fun getUserInfo(@Path("login")login: String): Single<UserInfo>

}